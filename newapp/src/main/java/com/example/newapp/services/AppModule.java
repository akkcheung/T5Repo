package com.example.newapp.services;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;

import org.apache.tapestry5.*;
import org.apache.tapestry5.ioc.Configuration;
import org.apache.tapestry5.ioc.MappedConfiguration;
import org.apache.tapestry5.ioc.MethodAdviceReceiver;
import org.apache.tapestry5.ioc.OrderedConfiguration;
import org.apache.tapestry5.ioc.ServiceBinder;
import org.apache.tapestry5.ioc.annotations.Contribute;
import org.apache.tapestry5.ioc.annotations.Local;
import org.apache.tapestry5.ioc.annotations.Match;
import org.apache.tapestry5.ioc.services.ThreadLocale;
import org.apache.tapestry5.services.LibraryMapping;
import org.apache.tapestry5.services.PageRenderLinkSource;
import org.apache.tapestry5.services.Request;
import org.apache.tapestry5.services.RequestFilter;
import org.apache.tapestry5.services.RequestHandler;
import org.apache.tapestry5.services.Response;
import org.apache.tapestry5.jpa.JpaEntityPackageManager;
import org.apache.tapestry5.jpa.JpaTransactionAdvisor;
import org.slf4j.Logger;

import com.example.newapp.entities.IPersonFinderServiceLocal;
import com.example.newapp.entities.IPersonManagerServiceLocal;
import com.example.newapp.entities.PersonFinderService;
import com.example.newapp.entities.PersonManagerService;
import com.example.newapp.util.MoneyTranslator;
import com.example.newapp.util.YesNoTranslator;
import com.example.newapp.validators.Letters;
import com.example.newapp.web.services.AssetProtectionFilter;
import com.example.newapp.web.services.SelectIdModelFactory;
import com.example.newapp.web.services.SelectIdModelFactoryImpl;

/**
 * This module is automatically included as part of the Tapestry IoC Registry, it's a good place to
 * configure and extend Tapestry, or to place your own service definitions.
 */
public class AppModule
{
    public static void bind(ServiceBinder binder)
    {
        // binder.bind(MyServiceInterface.class, MyServiceImpl.class);

        // Make bind() calls on the binder object to define most IoC services.
        // Use service builder methods (example below) when the implementation
        // is provided inline, or requires more initialization than simply
        // invoking the constructor.
    	

    	//2015.03
    	binder.bind(IPersonFinderServiceLocal.class, PersonFinderService.class);
    	
    	// binder.bind(CountryNames.class);    	
    	
    	binder.bind(IPersonManagerServiceLocal.class, PersonManagerService.class);
    	
    	binder.bind(SelectIdModelFactory.class, SelectIdModelFactoryImpl.class);
    }

    public static void contributeFactoryDefaults(
            MappedConfiguration<String, Object> configuration)
    {
        // The application version number is incorprated into URLs for some
        // assets. Web browsers will cache assets because of the far future expires
        // header. If existing assets are changed, the version number should also
        // change, to force the browser to download new versions. This overrides Tapesty's default
        // (a random hexadecimal number), but may be further overriden by DevelopmentModule or
        // QaModule.
        configuration.override(SymbolConstants.APPLICATION_VERSION, "1.0-SNAPSHOT");
        
        
    }

    public static void contributeApplicationDefaults(
            MappedConfiguration<String, Object> configuration)
    {
        // Contributions to ApplicationDefaults will override any contributions to
        // FactoryDefaults (with the same key). Here we're restricting the supported
        // locales to just "en" (English). As you add localised message catalogs and other assets,
        // you can extend this list of locales (it's a comma separated series of locale names;
        // the first locale name is the default when there's no reasonable match).
        configuration.add(SymbolConstants.SUPPORTED_LOCALES, "en");
        
        configuration.add(SymbolConstants.HMAC_PASSPHRASE, "HowAreYouToday");
    }


    /**
     * This is a service definition, the service will be named "TimingFilter". The interface,
     * RequestFilter, is used within the RequestHandler service pipeline, which is built from the
     * RequestHandler service configuration. Tapestry IoC is responsible for passing in an
     * appropriate Logger instance. Requests for static resources are handled at a higher level, so
     * this filter will only be invoked for Tapestry related requests.
     * <p/>
     * <p/>
     * Service builder methods are useful when the implementation is inline as an inner class
     * (as here) or require some other kind of special initialization. In most cases,
     * use the static bind() method instead.
     * <p/>
     * <p/>
     * If this method was named "build", then the service id would be taken from the
     * service interface and would be "RequestFilter".  Since Tapestry already defines
     * a service named "RequestFilter" we use an explicit service id that we can reference
     * inside the contribution method.
     */
    public RequestFilter buildTimingFilter(final Logger log)
    {
        return new RequestFilter()
        {
            public boolean service(Request request, Response response, RequestHandler handler)
                    throws IOException
            {
                long startTime = System.currentTimeMillis();

                try
                {
                    // The responsibility of a filter is to invoke the corresponding method
                    // in the handler. When you chain multiple filters together, each filter
                    // received a handler that is a bridge to the next filter.

                    return handler.service(request, response);
                } finally
                {
                    long elapsed = System.currentTimeMillis() - startTime;

                    log.info(String.format("Request time: %d ms", elapsed));
                }
            }
        };
    }

    /**
     * This is a contribution to the RequestHandler service configuration. This is how we extend
     * Tapestry using the timing filter. A common use for this kind of filter is transaction
     * management or security. The @Local annotation selects the desired service by type, but only
     * from the same module.  Without @Local, there would be an error due to the other service(s)
     * that implement RequestFilter (defined in other modules).
     */
    public void contributeRequestHandler(OrderedConfiguration<RequestFilter> configuration,
                                         @Local
                                         RequestFilter filter)
    {
        // Each contribution to an ordered configuration has a name, When necessary, you may
        // set constraints to precisely control the invocation order of the contributed filter
        // within the pipeline.

        configuration.add("Timing", filter);
    }
    
    //2015.03
    
    @Contribute(JpaEntityPackageManager.class)
    public static void providePackages(Configuration<String> configuration) {
  
       configuration.add("com.example.newapp.domain");
       // configuration.add("com.example.newapp.entities");
       
    }
    
    // Tell Tapestry about our custom translators, validators, and their message files.
    // We do this by contributing configuration to Tapestry's TranslatorAlternatesSource service, FieldValidatorSource
    // service, and ComponentMessagesSource service.

    @SuppressWarnings("rawtypes")
    public static void contributeTranslatorAlternatesSource(MappedConfiguration<String, Translator> configuration,
            ThreadLocale threadLocale) {
        configuration.add("yesno", new YesNoTranslator("yesno"));
        configuration.add("money2", new MoneyTranslator("money2", 2, threadLocale));
    }
    
    @SuppressWarnings("rawtypes")
    public static void contributeFieldValidatorSource(MappedConfiguration<String, Validator> configuration) {
        configuration.add("letters", new Letters());
    }
    
    @Match("*IPersonManagerServiceLocal")
    public static void adviseTransactionally(
          JpaTransactionAdvisor advisor,
          MethodAdviceReceiver receiver) {
  
    		advisor.addTransactionCommitAdvice(receiver);
    }
    
    /*
    public static void contributeComponentClassResolver(Configuration<LibraryMapping> configuration)
    {
        // Creates a virtual root pacakge for pages,components.
        configuration.add(new LibraryMapping("Confirm", "com.example.newapp.web.mixins"));
    }
    */
    
    // Tell Tapestry how to block access to WEB-INF/, META-INF/, and assets that are not in our assets "whitelist".
    // We do this by contributing a custom RequestFilter to Tapestry's RequestHandler service.
    // - This is necessary due to https://issues.apache.org/jira/browse/TAP5-815 .
    // - RequestHandler is shown in http://tapestry.apache.org/request-processing.html#RequestProcessing-Overview .
    // - RequestHandler is described in http://tapestry.apache.org/request-processing.html
    // - Based on an entry in the Tapestry Users mailing list by martijn.list on 15 Aug 09.

    public void contributeRequestHandler(OrderedConfiguration<RequestFilter> configuration,
            PageRenderLinkSource pageRenderLinkSource) {
        final HashSet<String> ASSETS_WHITE_LIST = new HashSet<String>(Arrays.asList("jpg", "jpeg", "png", "gif", "js",
                "css", "ico"));
        configuration.add("AssetProtectionFilter", new AssetProtectionFilter(ASSETS_WHITE_LIST, pageRenderLinkSource),
                "before:*");
    }
    
}

package com.example.newapp.pages.examples.component;

import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.ValueEncoder;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.corelib.components.Tree;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.tree.DefaultTreeModel;
import org.apache.tapestry5.tree.TreeModel;

import com.example.newapp.pages.Index;
import com.example.newapp.model.Stuff;
import com.example.newapp.model.StuffTreeModelAdapter;

public class TreeBrowse {
// Screen fields
    
    private TreeModel<Stuff> stuffModel;

    // Generally useful bits and pieces

    @InjectComponent
    private Tree tree;

    @Inject
    private ComponentResources componentResources;

    // The code

    void onClearExpansions() {
        tree.clearExpansions();
    }

    // Getters and setters
    
    public TreeModel<Stuff> getStuffModel() {

        if (stuffModel == null) {
            ValueEncoder<Stuff> stuffEncoder = new ValueEncoder<Stuff>() {

                @Override
                public String toClient(Stuff stuff) {
                    return stuff.uuid;
                }

                @Override
                public Stuff toValue(String uuid) {
                    return Stuff.ROOT.searchSubTree(uuid);
                }

            };
            stuffModel = new DefaultTreeModel<Stuff>(stuffEncoder, new StuffTreeModelAdapter(), Stuff.ROOT.children);
        }
        return stuffModel;
    }

    Object onHome() {
        componentResources.discardPersistentFieldChanges();
        return Index.class;
    }
}

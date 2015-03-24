package com.example.newapp.model;

import java.util.List;

import org.apache.tapestry5.tree.TreeModelAdapter;

public class StuffTreeModelAdapter  implements TreeModelAdapter<Stuff>{
	
	@Override
    public boolean isLeaf(Stuff stuff) {
        return !hasChildren(stuff);
    }

    @Override
    public boolean hasChildren(Stuff stuff) {
        return stuff.children != null && !stuff.children.isEmpty();
    }

    @Override
    public List<Stuff> getChildren(Stuff stuff) {
        return stuff.children;
    }

    @Override
    public String getLabel(Stuff stuff) {
        return stuff.name;
    }
}

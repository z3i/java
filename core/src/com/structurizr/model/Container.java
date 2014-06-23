package com.structurizr.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class Container extends Element {

    private SoftwareSystem parent;
    private String technology;

    private Set<Component> components = new HashSet<>();

    @Override
    public ElementType getType() {
        return ElementType.Container;
    }

    @JsonIgnore
    public SoftwareSystem getParent() {
        return parent;
    }

    public void setParent(SoftwareSystem parent) {
        this.parent = parent;
    }

    public String getTechnology() {
        return technology;
    }

    public void setTechnology(String technology) {
        this.technology = technology;
    }

    public void uses(Container destination, String description) {
        Relationship relationship = new Relationship(this, destination, description);
        relationships.add(relationship);
    }

    public void uses(SoftwareSystem destination, String description) {
        addRelationship(new Relationship(this, destination, description));
    }

    public Component createComponentWithClass(String fullyQualifiedClassName, String description) {
        return getModel().createComponentWithClass(this, fullyQualifiedClassName, description);
    }

    public void add(Component component) {
        components.add(component);
    }

    public Set<Component> getComponents() {
        return components;
    }

    public Component getComponentWithName(String name) {
        Optional<Component> component = components.stream().filter(c -> c.getName().equals(name)).findFirst();

        if (component.isPresent()) {
            return component.get();
        } else {
            return null;
        }
    }

}
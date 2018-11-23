package com.javeriana.sdp.controllers.students;

import com.javeriana.sdp.controllers.descriptors.Descriptor;

/**
 * Created by Sebastian on 21/11/18
 * Email: Juan.2114@hotmail.com
 * Email: Juan2114@javerianacali.edu.co
 */
public class StudentInfoModifierDescriptor implements Descriptor {

    /**
     * Represents the main topic of the descriptor
     */
    private String mainTopic;

    /**
     * Represents the previous topic name
     */
    private String previousTopicName;

    /**
     * Represents the new topic name
     */
    private String newTopicName;

    /**
     * Represents the new ulr
     */
    private String newUrl;

    /**
     * Represents the resource section
     */
    private int resourceSection;

    /**
     * Gets the resource section
     * @return  the resource section
     */
    public int getResourceSection() {
        return resourceSection;
    }

    /**
     * Sets the resource section to a desired section
     * @param resourceSection   the new resource section
     */
    public void setResourceSection(final int resourceSection) {
        this.resourceSection = resourceSection;
    }

    /**
     * Represents the main topic name
     */
    public String getMainTopic() {
        return mainTopic;
    }

    /**
     * Sets the main topic name to a main topic
     * @param mainTopic the main topic
     */
    public void setMainTopic(String mainTopic) {
        this.mainTopic = mainTopic;
    }

    /**
     * Represents the previous topic name
     */
    public String getPreviousTopicName() {
        return previousTopicName;
    }

    /**
     * Sets the previous topic name to a new desired name
     * @param previousTopicName the new name
     */
    public void setPreviousTopicName(String previousTopicName) {
        this.previousTopicName = previousTopicName;
    }

    /**
     * Represents the new topic name
     */
    public String getNewTopicName() {
        return newTopicName;
    }

    /**
     * Sets the new topic name to a new desired name
     * @param newTopicName the new name
     */
    public void setNewTopicName(String newTopicName) {
        this.newTopicName = newTopicName;
    }

    /**
     * Represents the new url
     */
    public String getNewUrl() {
        return newUrl;
    }

    /**
     * Sets the new url to a new desired url
     * @param newUrl the new url
     */
    public void setNewUrl(String newUrl) {
        this.newUrl = newUrl;
    }

    /**
     * Returns the SQL representation of this insertion
     * @return  the sql representation
     */
    public String toSql() {
        return "INSERT INTO STUDENTSINFO (Id, Text, Redirect, ResourceSection) VALUES (null, '" + newTopicName + "', '" + newUrl + "', '" + resourceSection + "')";
    }
}

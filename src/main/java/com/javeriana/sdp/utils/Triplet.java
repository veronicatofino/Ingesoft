package com.javeriana.sdp.utils;

/**
 * Created by Sebastian on 25/11/18
 * Email: Juan.2114@hotmail.com
 * Email: Juan2114@javerianacali.edu.co
 */
public class Triplet<I, J, K> {

    /**
     * Represents the left component of the triplet
     */
    private I left;

    /**
     * Represents the middle element
     */
    private J mid;

    /**
     * Represents the right component of the triplet
     */
    private K right;

    /**
     * Constructs a pair based on the left and right components
     * @param left  the left component of the triplet
     * @param mid   the middle component
     * @param right the right component of the triplet
     */
    public Triplet(final I left, final J mid, final K right) {
        this.left = left;
        this.mid = mid;
        this.right = right;
    }


    /**
     * Gets the left component of the triplet
     * @return  the left component of the triplet
     */
    public I getLeft() {
        return left;
    }

    /**
     * Gets the mid component of the triplet
     * @return  the mid component of the triplet
     */
    public J getMid() {
        return mid;
    }

    /**
     * Gets the right component of the triplet
     * @return  the right component of the triplet
     */
    public K getRight() {
        return right;
    }
}

package com.javeriana.sdp.utils;

/**
 * Created by Sebastian on 21/11/18
 * Email: Juan.2114@hotmail.com
 * Email: Juan2114@javerianacali.edu.co
 */
public class Pair<L, R> {

    /**
     * Represents the left component of the pair
     */
    private L left;

    /**
     * Represents the right component of the pair
     */
    private R right;

    /**
     * Constructs a pair based on the left and right components
     * @param left  the left component of the pair
     * @param right the right component of the pair
     */
    public Pair(final L left, final R right) {
        this.left = left;
        this.right = right;
    }

    /**
     * Sets the left component of the pair to a desired component
     * @param left  the left component
     * @return  this instance to allow chaining
     */
    public Pair<L, R> setLeft(final L left) {
        this.left = left;
        return this;
    }

    /**
     * Sets the right component of the pair to a desired component
     * @param right the right component
     * @return  this instance to allow chaining
     */
    public Pair<L, R> setRight(final R right) {
        this.right = right;
        return this;
    }

    /**
     * Gets the left component of the pair
     * @return  the left component of the pair
     */
    public L getLeft() {
        return left;
    }

    /**
     * Gets the right component of the pair
     * @return  the right component of the pair
     */
    public R getRight() {
        return right;
    }
}

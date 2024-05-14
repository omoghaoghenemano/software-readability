/**
     * Tests the equality of this object against an arbitrary Object.
     * <P>
     * This method will return true ONLY if the object is a Second object
     * representing the same second as this instance.
     *
     * @param obj  the object to compare (<code>null</code> permitted).
     *
     * @return <code>true</code> if second and minute of this and the object
     *         are the same.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Second)) {
            return false;
        }
        Second that = (Second) obj;
        if (this.second != that.second) {
            return false;
        }
        if (this.minute != that.minute) {
            return false;
        }
        if (this.hour != that.hour) {
            return false;
        }
        if (!this.day.equals(that.day)) {
            return false;
        }
        return true;
    }
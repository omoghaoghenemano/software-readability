/**
     * Tests this instance for equality with an arbitrary object.
     *
     * @param obj  the object (<code>null</code> permitted).
     *
     * @return A boolean.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof OHLC)) {
            return false;
        }
        OHLC that = (OHLC) obj;
        if (this.open != that.open) {
            return false;
        }
        if (this.close != that.close) {
            return false;
        }
        if (this.high != that.high) {
            return false;
        }
        if (this.low != that.low) {
            return false;
        }
        return true;
    }
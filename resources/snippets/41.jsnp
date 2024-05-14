/**
     * Returns the minimum and maximum values for the dataset's range
     * (y-values), assuming that the series in one category are stacked.
     *
     * @param dataset  the dataset (<code>null</code> not permitted).
     * @param base  the base value for the bars.
     *
     * @return The range (<code>null</code> if the dataset contains no values).
     */
    public static Range findStackedRangeBounds(CategoryDataset dataset,
            double base) {
        ParamChecks.nullNotPermitted(dataset, "dataset");
        Range result = null;
        double minimum = Double.POSITIVE_INFINITY;
        double maximum = Double.NEGATIVE_INFINITY;
        int categoryCount = dataset.getColumnCount();
        for (int item = 0; item < categoryCount; item++) {
            double positive = base;
            double negative = base;
            int seriesCount = dataset.getRowCount();
            for (int series = 0; series < seriesCount; series++) {
                Number number = dataset.getValue(series, item);
                if (number != null) {
                    double value = number.doubleValue();
                    if (value > 0.0) {
                        positive = positive + value;
                    }
                    if (value < 0.0) {
                        negative = negative + value;
                        // '+', remember value is negative
                    }
                }
            }
            minimum = Math.min(minimum, negative);
            maximum = Math.max(maximum, positive);
        }
        if (minimum <= maximum) {
            result = new Range(minimum, maximum);
        }
        return result;

    }
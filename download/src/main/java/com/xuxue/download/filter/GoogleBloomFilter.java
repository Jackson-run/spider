package com.xuxue.download.filter;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

import java.nio.charset.Charset;

public class GoogleBloomFilter implements Filter<CharSequence> {

    private BloomFilter<CharSequence> filter;

    private int filterSize;

    private double errorRate;

    public GoogleBloomFilter(int filterSize, double errorRate) {
        this.filterSize = filterSize;
        this.errorRate = errorRate;
        this.filter = BloomFilter.create(Funnels.stringFunnel(Charset.forName("UTF-8")), filterSize, errorRate);
    }

    public boolean contain(CharSequence charSequence) {
        return this.filter.mightContain(charSequence);
    }

    public void add(CharSequence charSequnence) {
        this.filter.put(charSequnence);
    }

    public BloomFilter<CharSequence> getFilter() {
        return filter;
    }

    public void setFilter(BloomFilter<CharSequence> filter) {
        this.filter = filter;
    }

    public int getFilterSize() {
        return filterSize;
    }

    public void setFilterSize(int filterSize) {
        this.filterSize = filterSize;
    }

    public double getErrorRate() {
        return errorRate;
    }

    public void setErrorRate(double errorRate) {
        this.errorRate = errorRate;
    }


}

package com.systelab.seed.util.pagination;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Page<T> implements Serializable {

    private long totalElements;

    @XmlAnyElement(lax = true)
    private List<T> content;

    public Page() {
        this.content = new ArrayList();
        this.totalElements = 0;
    }

    public Page(List<T> content, long total) {
        this.content = content;
        this.totalElements = total;
    }

    public void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public List<T> getContent() {
        return content;
    }
}
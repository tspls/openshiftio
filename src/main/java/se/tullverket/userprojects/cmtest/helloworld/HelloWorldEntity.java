/*
 * Copyright (C) 2017 Swedish Customs. All rights reserved.
 *
 * This file is part of Swedish Customs Software.
 * For details on use and redistribution please refer to
 * the LICENSE.txt file included with these sources.
 */
package se.tullverket.userprojects.cmtest.helloworld;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;
import javax.persistence.Entity;

/**
 * <p>
 * TODO: [Beskrivning] 
 * </p>
 * <p>
 * @author $Author: tspls$
 * </p>
 */
@Entity
public class HelloWorldEntity implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;
    @Version
    @Column(name = "version")
    private int version;
    
    private String phrase;

    public String getPhrase() {
        return phrase;
    }

    public void setPhrase(String phrase) {
        this.phrase = phrase;
    }

}

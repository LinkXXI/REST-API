package org.rogue.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by Kevin on 2015-04-07.
 */
@Entity
public class Address implements Serializable {

    @Id
    @GeneratedValue
    private int id;
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cms.client.models;

/**
 *
 * @author Moham
 */
public abstract class Model {
    abstract boolean save();
    abstract boolean update();
    abstract boolean delete();
    abstract Model get(String... v);
    abstract Model[] getAll(String... v);
    
}

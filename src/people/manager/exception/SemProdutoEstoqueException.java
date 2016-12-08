/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package people.manager.exception;

import java.util.ArrayList;

/**
 *
 * @author marcos
 */
public class SemProdutoEstoqueException extends Exception {
    private final ArrayList produtos;
    public SemProdutoEstoqueException(ArrayList prod) {
        this.produtos = prod;
    }
    public ArrayList getProdutos(){
        return this.produtos;
    }
    
}

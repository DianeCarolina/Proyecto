/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uce.medicina.seguimiento.dto;

import ec.edu.uce.medicina.seguimiento.modelo.Pregunta;

/**
 *
 * @author CAROLINE
 */
public class ResultadoAuxDTO {

    private Pregunta idPregunta;
    private String numCatRes;
    private String[] arrayResp;
    private Integer idResultado;

    public ResultadoAuxDTO() {
    }
    

    public ResultadoAuxDTO(Pregunta idPregunta, String numCatResc) {
        this.idPregunta = idPregunta;
        this.numCatRes = numCatResc;
        
    }
    public ResultadoAuxDTO(Integer idResultado,Pregunta idPregunta, String numCatResc) {
        this.idResultado= idResultado;
        this.idPregunta = idPregunta;
        this.numCatRes = numCatResc;
        
    }

   
    public Pregunta getIdPregunta() {
        return idPregunta;
    }

    //get y set
    public void setIdPregunta(Pregunta idPregunta) {
        this.idPregunta = idPregunta;
    }

    public String getNumCatRes() {
        return numCatRes;
    }

    public void setNumCatRes(String numCatRes) {
        this.numCatRes = numCatRes;
    }

    public String[] getArrayResp() {
        return arrayResp;
    }

    public void setArrayResp(String[] arrayResp) {
        this.arrayResp = arrayResp;
    }

    public Integer getIdResultado() {
        return idResultado;
    }

    public void setIdResultado(Integer idResultado) {
        this.idResultado = idResultado;
    }
    
}

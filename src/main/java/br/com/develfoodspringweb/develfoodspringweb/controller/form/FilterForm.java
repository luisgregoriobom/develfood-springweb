package br.com.develfoodspringweb.develfoodspringweb.controller.form;

import lombok.Data;

@Data
public class FilterForm {

    public int skip = 0;
    public int take = 5;
    public String search;

}

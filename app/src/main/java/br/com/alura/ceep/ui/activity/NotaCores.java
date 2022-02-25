package br.com.alura.ceep.ui.activity;

import android.graphics.drawable.Drawable;

public enum NotaCores {

    BRANCO("#FFFFFF"),
    AZUL("#408EC9"),
    AMARELO("#F9F256"),
    VERMELHO("#EC2F4B"),
    VERDE("#9ACD32"),
    LILAS("#F1CBFF"),
    CINZA("#D2D4DC"),
    MARROM("#A47C48"),
    ROXO("#BE29EC");

    private final String cor;

    NotaCores(String cor) {
        this.cor = cor;
    }

    public String getCor() {
        return cor;
    }
}

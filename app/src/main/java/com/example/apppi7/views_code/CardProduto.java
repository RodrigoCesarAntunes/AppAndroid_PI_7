package com.example.apppi7.views_code;

import android.media.Image;
import android.widget.TextView;

import com.example.apppi7.model.Produto;

public class CardProduto {

    private String imgCardUri;
    private String txtPrecoCard;
    private String txtNomeCard;

    private Produto _produto;

    public CardProduto(String imgResource, String precoCard, String nomeCard, Produto produto)
    {
        imgCardUri = imgResource;
        txtNomeCard = nomeCard;
        txtPrecoCard = precoCard;
        _produto = produto;
    }

    public String getImgCardUri() {
        return imgCardUri;
    }

    public String getTxtPrecoCard() {
        return txtPrecoCard;
    }

    public String getTxtNomeCard() {
        return txtNomeCard;
    }





}

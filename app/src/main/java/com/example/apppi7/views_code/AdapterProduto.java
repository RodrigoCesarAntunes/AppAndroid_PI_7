package com.example.apppi7.views_code;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.apppi7.HomeActivity;
import com.example.apppi7.R;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.ArrayList;

public class AdapterProduto extends RecyclerView.Adapter<AdapterProduto.ProdutoViewHolder> {

    private IOnItemClickListener _listener;

    public interface IOnItemClickListener{
        void onItemClick(int position);
    }

    public void setOnItemClickListener(IOnItemClickListener listener)
    {
        _listener = listener;
    }

    public static class ProdutoViewHolder extends RecyclerView.ViewHolder
    {

        public ImageView imgCard;
        public TextView txtPreco;
        public TextView txtNome;

        public ProdutoViewHolder(View itemView, final IOnItemClickListener innerListener) {
            super(itemView);

            imgCard = itemView.findViewById(R.id.imgProdutoCard);
            txtNome = itemView.findViewById(R.id.txtNomeProdutoCard);
            txtPreco = itemView.findViewById(R.id.txtPrecoProdutoCard);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(innerListener != null)
                    {
                        int posistion = getAdapterPosition();
                        if(posistion != RecyclerView.NO_POSITION)
                        {
                            innerListener.onItemClick(posistion);
                        }
                    }
                }
            });
        }
    }

    private ArrayList<CardProduto> _cardProdutos;

    public AdapterProduto(ArrayList<CardProduto> cardProdutos)
    {
        _cardProdutos = cardProdutos;
    }

    @Override
    public ProdutoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_produto, parent, false);
        ProdutoViewHolder produtoVH = new ProdutoViewHolder(v, _listener);
        return produtoVH;
    }

    @Override
    public void onBindViewHolder(ProdutoViewHolder holder, int position) {
        CardProduto produto = _cardProdutos.get(position);

        holder.txtPreco.setText("R$ " + produto.getTxtPrecoCard() + "0");
        holder.txtNome.setText(produto.getTxtNomeCard());

        ImageLoader imageLoader = ImageLoader.getInstance();
        imageLoader.init(ImageLoaderConfiguration.createDefault(HomeActivity.getContext()));

        imageLoader.displayImage(produto.getImgCardUri(), holder.imgCard);

    }

    @Override
    public int getItemCount() {
        return _cardProdutos.size();
    }
}

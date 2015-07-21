package sesec.fortaleza.ce.gov.br.ipe.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import sesec.fortaleza.ce.gov.br.ipe.R;
import sesec.fortaleza.ce.gov.br.ipe.model.Atendimento;

/**
 * Created by Jorge on 29/06/2015.
 */
public class AtendimentoAdapter extends BaseAdapter {

    private LayoutInflater layoutInflater;
    private List<Atendimento> atendimentos;
    private String[] bgColors;
    private final Context ctx;

    public AtendimentoAdapter(Context ctx, List<Atendimento> atendimentos){
        this.ctx = ctx;
        this.atendimentos = atendimentos;
        layoutInflater = LayoutInflater.from(ctx);

    }

    @Override
    public int getCount() {
        return atendimentos.size();
    }

    @Override
    public Object getItem(int position) {
        return atendimentos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(
                    R.layout.list_row, null);
            holder = new ViewHolder();
            holder.protocolo = (TextView) convertView
                    .findViewById(R.id.protocol);
            holder.titulo = (TextView) convertView
                    .findViewById(R.id.title);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Atendimento atendimento = atendimentos.get(position);

        holder.protocolo.setText(atendimento.getProtocolo());
        holder.titulo.setText(atendimento.getTitulo());
        return convertView;
    }
    static class ViewHolder {
        TextView protocolo;
        TextView titulo;
    }
}

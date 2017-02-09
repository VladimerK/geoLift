package ge.idevelopers.Lifti.app;

/**
 * Created by user on 07.02.2017.
 */

    import android.support.v7.widget.RecyclerView;
    import android.view.LayoutInflater;
    import android.view.View;
    import android.view.ViewGroup;
    import android.widget.ImageView;
    import android.widget.TextView;

    import java.util.List;

 public class PartnerAdapter extends RecyclerView.Adapter<PartnerAdapter.MyViewHolder> {

        private List<PartnerRes> partnerlist;

        public class MyViewHolder extends RecyclerView.ViewHolder {
            public TextView title;
            public ImageView partnerimage;

            public MyViewHolder(View view) {
                super(view);
                title = (TextView) view.findViewById(R.id.title);
                partnerimage = (ImageView) view.findViewById(R.id.partnerImg);
            }
        }


        public PartnerAdapter(List<PartnerRes> partnerlist) {
            this.partnerlist = partnerlist;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.list_row, parent, false);

            return new MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            PartnerRes partnerRes = partnerlist.get(position);
            holder.title.setText(partnerRes.getTitle());
            holder.partnerimage.setImageResource(partnerRes.getImg());

        }

        @Override
        public int getItemCount() {
            return partnerlist.size();
        }
    }

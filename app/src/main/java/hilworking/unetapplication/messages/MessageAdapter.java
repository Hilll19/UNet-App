package hilworking.unetapplication.messages;
//
//public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MyViewHolder> {
//
//    private final List<MesaageList> mesaageLists;
//    private final Context context;
//
//    public MessageAdapter(List<MesaageList> mesaageLists, Context context) {
//        this.mesaageLists = mesaageLists;
//        this.context = context;
//    }
//
//    @NonNull
//    @Override
//    public MessageAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.message_adapter_layout, null));
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull MessageAdapter.MyViewHolder holder, int position) {
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return mesaageLists.size();
//    }
//    static class MyViewHolder extends RecyclerView.ViewHolder {
//
//        private CircleImageView profilePic;
//        private TextView name;
//        private TextView lastMessage;
//        private TextView unseenMessage;
//        public MyViewHolder(@NonNull View itemView) {
//
//            super(itemView);
//
//            profilePic = itemView.findViewById(R.id.profilePic);
//            name = itemView.findViewById(R.id.name);
//            lastMessage = itemView.findViewById(R.id.lastMessage);
//            unseenMessage = itemView.findViewById(R.id.unseenMessage);
//
//        }
//    }
//}

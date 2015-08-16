package nyc.jackcook.fox;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import nyc.jackcook.fox.util.RequestManager;
import nyc.jackcook.fox.util.TransactionsAdapter;

public class TransactionsFragment extends Fragment {

    private TransactionsAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        RequestManager.getTransactions(new Runnable() {
            @Override
            public void run() {
                adapter.updateWithTransactions(RequestManager.transactions);
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_transactions, container, false);

        adapter = new TransactionsAdapter(getActivity());

        ListView transactionsList = (ListView) view.findViewById(R.id.transactions_list);
        transactionsList.setAdapter(adapter);

        return view;
    }
}

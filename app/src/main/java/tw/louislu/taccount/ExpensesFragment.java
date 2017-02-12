package tw.louislu.taccount;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;


/**
 * A simple {@link Fragment} subclass.
 */
public class ExpensesFragment extends Fragment {
    //private static final float TEXT_SIZE = 24;
    private EventActivity _parent;
    private ListView _expensesListView;
    private ExpensesListAdapter _expensesListAdapter;

    public ExpensesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof Activity){
            this._parent = (EventActivity)context;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_expenses, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initFindViewById();
        setListAdapter();
    }

    // 取得View元件
    private void initFindViewById(){
        _expensesListView = (ListView) getView().findViewById(R.id.expenses_expensesListView);
    }

    //設定List Adapter
    private void setListAdapter(){
        _expensesListAdapter = new ExpensesListAdapter(_parent.getApplicationContext(), _parent.getExpensesList());
        _expensesListView.setAdapter(_expensesListAdapter);
    }

    //通知支出list有變更
    public void notifyDataSetChanged(){
        _expensesListAdapter.notifyDataSetChanged();
    }
}

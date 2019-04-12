package it.dut.thaixoan.applazada.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Point;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import it.dut.thaixoan.applazada.R;
import it.dut.thaixoan.applazada.models.object_class.LoaiSanPham;
import it.dut.thaixoan.applazada.models.trangchu.xulymenu.XuLyJsonMenu;

public class ExpanableAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<LoaiSanPham> loaiSanPhams;

    public ExpanableAdapter(Context context, List<LoaiSanPham> loaiSanPhams) {
        this.context = context;
        this.loaiSanPhams = loaiSanPhams;

        XuLyJsonMenu xuLyJsonMenu = new XuLyJsonMenu();

        int count = loaiSanPhams.size();
        for (int i = 0; i < count; i++) {
            int maLoaiSanPham = loaiSanPhams.get(i).getMaLoaiSanPham();
            loaiSanPhams.get(i).setListCon(xuLyJsonMenu.layLoaiSanPhamTheoMaLoaiSanPham(maLoaiSanPham));
        }


    }

    @Override
    public int getGroupCount() {
        return loaiSanPhams.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        if (loaiSanPhams.get(groupPosition).getListCon().size() != 0) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public Object getGroup(int groupPosition) {
        return loaiSanPhams.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return loaiSanPhams.get(groupPosition).getListCon().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return loaiSanPhams.get(groupPosition).getMaLoaiSanPham();
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return loaiSanPhams.get(groupPosition).getListCon().get(childPosition).getMaLoaiSanPham();
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    public class ViewHolderMenu {
        TextView textViewTenLoaiSanPham;
        ImageView imageIconAddOrRemove;
    }

    @Override
    public View getGroupView(final int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        View view = convertView;
        ViewHolderMenu viewHolderMenu;
        if (view == null) {
            viewHolderMenu = new ViewHolderMenu();
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            assert layoutInflater != null;
            view = layoutInflater.inflate(R.layout.custom_layout_group_view, parent, false);
            viewHolderMenu.textViewTenLoaiSanPham = view.findViewById(R.id.text_view_ten_loai_san_pham);
            viewHolderMenu.imageIconAddOrRemove = view.findViewById(R.id.image_icon_add_or_remove);
            view.setTag(viewHolderMenu);

        } else {
            viewHolderMenu = (ViewHolderMenu) view.getTag();
        }

        viewHolderMenu.textViewTenLoaiSanPham.setText(loaiSanPhams.get(groupPosition).getTenLoaiSanPham());

        boolean isChild = loaiSanPhams.get(groupPosition).getListCon().size() > 0;

        if (isChild) {
            viewHolderMenu.imageIconAddOrRemove.setVisibility(View.VISIBLE);
        } else {
            viewHolderMenu.imageIconAddOrRemove.setVisibility(View.INVISIBLE);
        }

        if (isExpanded) {
            viewHolderMenu.imageIconAddOrRemove.setImageResource(R.drawable.ic_remove_black_24dp);
            view.setBackgroundResource(R.color.gray);
        } else {
            viewHolderMenu.imageIconAddOrRemove.setImageResource(R.drawable.ic_add_black_24dp);
            view.setBackgroundResource(R.color.white_color);
        }

        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.d("setOnTouchListener", " : " + loaiSanPhams.get(groupPosition).getTenLoaiSanPham() + " - " + loaiSanPhams.get(groupPosition).getMaLoaiSanPham());
                return false;
            }
        });

        return view;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        SecondExpanable secondExpanable = new SecondExpanable(context);

        ExpanableAdapter secondAdapter = new ExpanableAdapter(context, loaiSanPhams.get(groupPosition).getListCon());
        secondExpanable.setAdapter(secondAdapter);
        secondExpanable.setGroupIndicator(null);
        notifyDataSetChanged();
        return secondExpanable;
    }

    public class SecondExpanable extends ExpandableListView {

        public SecondExpanable(Context context) {
            super(context);
        }

        @SuppressLint("DrawAllocation")
        @Override
        protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

            WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            assert windowManager != null;
            Display display = windowManager.getDefaultDisplay();
            Point size = new Point();
            display.getSize(size);
            int heightDevice = size.y;

            heightMeasureSpec = MeasureSpec.makeMeasureSpec(heightDevice, MeasureSpec.AT_MOST);
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}

package com.example.stylebook;

import android.app.DatePickerDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.stylebook.db.Cloth;
import com.jrummyapps.android.colorpicker.ColorPanelView;
import com.jrummyapps.android.colorpicker.ColorPickerDialog;
import com.jrummyapps.android.colorpicker.ColorPickerDialogListener;
import com.jrummyapps.android.colorpicker.ColorPickerView;

import java.text.SimpleDateFormat;
import java.time.Month;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static java.lang.reflect.Array.set;

public class ClosetAddActivity extends AppCompatActivity {
    private static final String TAG = "ClosetAddActivity";
    private ColorPanelView colorPickerViewModel;
    private EditText editName,editMaterial;
    private Spinner season,temprature,types;
    private Button buyTime;
    private Calendar pickDate=Calendar.getInstance();
    private List<String>getSeasonData(){
        List<String> dataList = new ArrayList<String>();
        dataList.add("Spring");
        dataList.add("Summer");
        dataList.add("Fall");
        dataList.add("Winter");
        return dataList;
    }
    private List<String>getTemData(){
        List<String> dataList = new ArrayList<String>();
        for (int i=1;i<=8;i++){
            dataList.add(i+"");
        }
        return dataList;
    }
    private List<String>getTypeData(){
        List<String> dataList = new ArrayList<String>();
        dataList.add("Coat");dataList.add("Sweater");dataList.add("Shirt");dataList.add("T-shirt");dataList.add("Hoodie");
        dataList.add("Dress");dataList.add("Skirt");dataList.add("Jeans");dataList.add("Trouser");dataList.add("Shorts");
        return dataList;
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.closet_item_add);
        final Cloth cloth = new Cloth();
        editName = (EditText) findViewById(R.id.edit_name);
        editMaterial = (EditText) findViewById(R.id.edi_material);
        season = (Spinner) findViewById(R.id.spinner_season);
        temprature = (Spinner) findViewById(R.id.spinner_temprature);
        types = (Spinner) findViewById(R.id.spinner_type);
        buyTime = (Button) findViewById(R.id.text_date_picker);
        buyTime.setText(pickDate.get(Calendar.YEAR)+"-"+(pickDate.get(Calendar.MONTH)+1)+"-"+pickDate.get(Calendar.DAY_OF_MONTH));
        buyTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickDlg();
            }
        });
        ArrayAdapter<String> seasonAdapter = new ArrayAdapter<String>(ClosetAddActivity.this,R.layout.spinner_item,getSeasonData());
        season.setAdapter(seasonAdapter);
        season.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                cloth.setSeason(position+1);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        ArrayAdapter<String> temAdapter = new ArrayAdapter<String>(ClosetAddActivity.this,R.layout.spinner_item,getTemData());
        temprature.setAdapter(temAdapter);
        temprature.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                cloth.settemprature(position+1);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        ArrayAdapter<String> typesAdapter = new ArrayAdapter<String>(ClosetAddActivity.this,R.layout.spinner_item,getTypeData());
        types.setAdapter(typesAdapter);
        types.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                cloth.setType(parent.getItemAtPosition(position).toString());
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        colorPickerViewModel = (ColorPanelView) findViewById(R.id.color_panel_view);
        colorPickerViewModel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opeAdvancenDialog();
            }
        });
        FloatingActionButton floartingdone=(FloatingActionButton)findViewById(R.id.floatingbar_done);
        floartingdone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cloth.setName(editName.getText().toString());
                cloth.setColor(Color.valueOf(colorPickerViewModel.getColor()));
                cloth.setMaterial(editMaterial.getText().toString());
                cloth.setBuyDate(pickDate);
                System.out.println(cloth.getBuyDate().get(Calendar.MONTH));
                System.out.println(cloth.getBuyDate().get(Calendar.DAY_OF_MONTH));
                System.out.println(cloth.getSeason());
                System.out.println(cloth.getColor());
                System.out.println(cloth.getMaterial());
                System.out.println(cloth.getName());
                System.out.println(cloth.gettemprature());
                System.out.println(cloth.getType());
                ClosetAddActivity.this.finish();
            }
        });
    }
    protected void showDatePickDlg() {
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                monthOfYear=monthOfYear+1;
                buyTime.setText(year + "-" + monthOfYear + "-" + dayOfMonth);
                pickDate.set(year,monthOfYear,dayOfMonth);
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }
    public static final int DIALGE_ID = 0;
    private void opeAdvancenDialog() {
        int color = colorPickerViewModel.getColor();
//传入的默认color
        ColorPickerDialog colorPickerDialog = ColorPickerDialog.newBuilder().setColor(color)
                .setDialogTitle(R.string.color_picker)
//设置dialog标题
//                .setDialogType(ColorPickerDialog.TYPE_CUSTOM)
//设置为自定义模式
                .setShowAlphaSlider(true)
//设置有透明度模式，默认没有透明度
                .setDialogId(DIALGE_ID)
//设置Id,回调时传回用于判断
                .setAllowPresets(false)
//不显示预知模式
                .create();
//Buider创建
        colorPickerDialog.setColorPickerDialogListener(pickerDialogListener);
//设置回调，用于获取选择的颜色
        colorPickerDialog.show(getFragmentManager(), "color-picker-dialog");
    }

    private ColorPickerDialogListener pickerDialogListener = new ColorPickerDialogListener() {
        @Override
        public void onColorSelected(int dialogId, @ColorInt int color) {
            if (dialogId == DIALGE_ID) {
                colorPickerViewModel.setColor(color);
            }
        }
        @Override
        public void onDialogDismissed(int dialogId) {

        }
    };
}


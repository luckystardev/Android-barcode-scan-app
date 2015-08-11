/**
 * 
 */
package com.aperfectpc.nuera_id.barcode.reader;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.aperfectpc.nuera_id.barcode.reader.R;
import com.aperfectpc.nuera_id.barcode.utils.BarcodeUtils;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

/**
 * @author mrpfisher
 * @author Wesley Young <wyoung@aperfectpc.com>
 */
public class ExamplesActivity extends ListActivity
{
  public static final Map<String, String> EXAMPLE_BARCODES;

  private List<Map<String, String>> mExampleMapList = new ArrayList<Map<String, String>>();

  static
  {
    // TODO: Download a JSON encoding of this static data.
    Map<String, String> map = new TreeMap<String, String>();
    map.put("AEC Demo 1", "0FJkmDOO25YK3m537Rmd4w==");
    map.put("AEC Demo 2", "YbkXEfnxdH_yicBMr1YNCg==");
    map.put("Kellogs Demo 1", "0vEiirWblikh2GJGOiRQlA==");
    map.put("Kellogs Demo 2", "aNzY1CKObg0AjMbAHXnfaA==");
    map.put("Kellogs Demo 3", "cLSPbhJyWl1E1akcz8KyVw==");
    map.put("Kellogs Demo 4", "t44wiKiAK6WCB6Z3BC_hPA==");
    map.put("Kellogs Demo 5", "vjIaJUU2hr0a1fgX-TXTwA==");
    map.put("NuEra-ID Demo", "4LMA1fvsrhfviXULfbaEtA==");
    map.put("Yellow Tail Demo 1", "BsNkkAUAJxz4hkYXR2CS2Q==");
    map.put("Yellow Tail Demo 2", "EbkB7sVM_YGlE5H4Eq8c2g==");
    map.put("Yellow Tail Demo 3", "dKKnSOwwIViAVGIqCe0u2g==");
    map.put("Yellow Tail Demo 4", "kx1rc1LbqCOv4g9Ate8f2g==");
    map.put("Yellow Tail Demo 5", "vCr5rDExu7v-QHox8wc72g==");
    map.put("Yellow Tail Demo 6", "x7eng64hirud5TJASFYF1A==");
    
    EXAMPLE_BARCODES = Collections.unmodifiableMap(map);
  }
  
  @Override
  public void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.examples_list);
    setTitle(R.string.title_examples);

    loadData();

    ListAdapter adapter = new SimpleAdapter(this, mExampleMapList,
      android.R.layout.simple_list_item_1, new String[] {"name"},
      new int[] { android.R.id.text1 });
    setListAdapter(adapter);
  }

  @Override
  protected void onListItemClick(ListView l, View v, int position, long id)
  {
    Map<?, ?> map = (Map<?, ?>)getListView().getItemAtPosition(position);
    String barcode = (String)map.get("barcode");
    
    // Toast.makeText(this, barcode, Toast.LENGTH_SHORT).show();
    BarcodeUtils.displayBarcodeInfo(this, DisplayActivity.class, barcode);
  }

  private void loadData()
  {
    for(String key : EXAMPLE_BARCODES.keySet())
    {
      HashMap<String, String> example = new HashMap<String, String>(2);
      example.put("name", key);
      example.put("barcode", EXAMPLE_BARCODES.get(key));
      mExampleMapList.add(example);
    }
  }

  // public class NuEraExampleAdapter extends BaseAdapter {
  // private ArrayList<Integer> mExampleIds;
  // private Context mContext;
  //
  // public NuEraExampleAdapter(Context c) {
  // mContext = c;
  // mExampleIds = new ArrayList<Integer>();
  //
  // mExampleIds.add(1);
  //
  //
  //
  // // if(!prefHolder.getName().isEmpty()) {
  // // mThumbIds.add(R.drawable.vcard);
  // // }
  // // if(!prefHolder.getEmail().isEmpty()) {
  // // mThumbIds.add(R.drawable.mail);
  // // }
  // // if(!prefHolder.getFacebook().isEmpty()) {
  // // mThumbIds.add(R.drawable.facebook);
  // // }
  // // if(!prefHolder.getTwitter().isEmpty()) {
  // // mThumbIds.add(R.drawable.twitter);
  // // }
  // // if(!prefHolder.getLinkedIn().isEmpty()) {
  // // mThumbIds.add(R.drawable.linkedin);
  // // }
  // // if(!prefHolder.getSkype().isEmpty()) {
  // // mThumbIds.add(R.drawable.skype);
  // // }
  // // R.drawable.scan, R.drawable.vcard,
  // // R.drawable.mail,
  // // R.drawable.facebook, R.drawable.twitter,
  // // R.drawable.linkedin,
  // // R.drawable.skype, R.drawable.ic_preferences,
  // //mThumbIds.add(R.drawable.ic_preferences);
  // }
  //
  // public int getCount() {
  // return mExampleIds.size();
  // }
  //
  // public Object getItem(int position) {
  // return position;
  // }
  //
  // public long getItemId(int position) {
  // if(position >= 0 && position <= mExampleIds.size()) {
  // return mExampleIds.get(position);
  // } else return -1;
  // }
  //
  // public View getView(int position, View convertView, ViewGroup parent) {
  // ImageView imageView;
  // if (convertView == null) {
  // imageView = new ImageView(mContext);
  // //imageView.setLayoutParams(new GridView.LayoutParams(100, 100));
  // imageView.setAdjustViewBounds(false);
  // //imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
  // imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
  // imageView.setPadding(5, 5, 5, 5);
  // } else {
  // imageView = (ImageView) convertView;
  // }
  //
  // imageView.setImageResource(mExampleIds.get(position));
  //
  // return imageView;
  // }
  // }
}

/**
 * 
 */
package com.aperfectpc.nuera_id.barcode.reader;

import com.aperfectpc.nuera_id.barcode.reader.R;
import com.aperfectpc.nuera_id.barcode.utils.BarcodeUtils;
import com.google.zxing.client.android.PreferencesActivity;
import com.google.zxing.integration.android.IntentResult;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

/**
 * @author mrpfisher
 * 
 */
public class MenuActivity extends Activity
{
  @Override
  public void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.menu_layout);

    Button scanButton = (Button) findViewById(R.id.button_scan);
    scanButton.setOnClickListener(new OnClickListener()
    {
      @Override
      public void onClick(View v)
      {
        BarcodeUtils.scanBarcode(MenuActivity.this);
      }
    });

    Button examplesButton = (Button) findViewById(R.id.button_examples);
    examplesButton.setOnClickListener(new OnClickListener()
    {
      @Override
      public void onClick(View v)
      {
        Intent i = new Intent(MenuActivity.this, ExamplesActivity.class);
        startActivity(i);
      }
    });
    
    Button cameraSettingsButton = (Button)findViewById(R.id.button_camera_settings);
    cameraSettingsButton.setOnClickListener(new OnClickListener()
    {
      @Override
      public void onClick(View v)
      {
        Intent i = new Intent(MenuActivity.this, PreferencesActivity.class);
        startActivity(i);
      }
    });
  }

  @Override
  public void onActivityResult(int requestCode, int resultCode, Intent intent)
  {
    if(resultCode != RESULT_CANCELED)
    {
      IntentResult scanResult = BarcodeUtils.parseActivityResult(requestCode, resultCode, intent);
      
      if(scanResult != null)
      {
        String result = scanResult.getContents();
        
        if(BarcodeUtils.looksLikeNuEra(result))
        {
          BarcodeUtils.displayBarcodeInfo(this, DisplayActivity.class, result);
        }
        else if("QR_CODE".equalsIgnoreCase(scanResult.getFormatName()))
        {
          Uri uri = Uri.parse(result);
          Intent i = new Intent(this, DisplayActivity.class);
          i.setAction(Intent.ACTION_VIEW);
          i.setData(uri);
          startActivity(i);
        }
        else
        {
          // Message: not a Nu-Era barcode or QR Code.
          Toast.makeText(this, result + " is not a NU-ERA or QR code",
              Toast.LENGTH_LONG).show();
        }
      }
      else
      {
        // Null scan result
      }
    }
    else
    {
      // Cancelled
      // Includes case where Barcode Reader was not installed and user elected
      // not to install it.*/
    }
  }

  // Button customViewButton =
  // (Button)findViewById(R.id.menu_button_custom_view);
  // customViewButton.setOnClickListener(new OnClickListener() {
  // public void onClick(View v) {
  // Intent i = new Intent(MenuActivity.this, CustomViewActivity.class);
  // startActivity(i);
  // }
  // });

  // Button quotesButton = (Button)findViewById(R.id.menu_button_quotes);
  // quotesButton.setOnClickListener(new OnClickListener() {
  // public void onClick(View v) {
  // Intent i = new Intent(MenuActivity.this, QuoteListActivity.class);
  // startActivity(i);
  // }
  // });
}

package com.izv.lectorplano;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		inicio();
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	private void inicio(){
		
		TextView tvTexto=(TextView)findViewById(R.id.tvTexto);
		
		//Se obtiene la ruta del archivo que vamos a mostrar
		Uri data=getIntent().getData();
		
		//Si no es nulo sera algo
		if(data!=null){
			
			//Se crea un File obteniendo la ruta de la Uri
			File archivo=new File(data.getPath());				
			
			try {
				
				//Se crea el buffer
				BufferedReader br=new BufferedReader(new FileReader(archivo));
				
				String linea;
				StringBuilder texto=new StringBuilder();
				
					//Mientras que tenga algo el archivo se va añadiendo a texto
					while((linea=br.readLine())!=null){
						texto.append(linea);
					}
				
				//Se cierra el buffer
				br.close();
				
				//Se escribe el texto en el textview
				tvTexto.setText(texto);
				
			} catch (FileNotFoundException e) {
				Toast.makeText(this, R.string.error_archivo_no_encontrado, Toast.LENGTH_SHORT).show();
			} catch (IOException e) {
				Toast.makeText(this, R.string.error_io, Toast.LENGTH_SHORT).show();
			}
			
		}
		
	}

}
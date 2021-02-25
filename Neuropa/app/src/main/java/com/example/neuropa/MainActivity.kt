package com.example.neuropa

import android.os.Bundle
import android.os.CountDownTimer
import android.view.Gravity
import android.view.View
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.system.exitProcess


class MainActivity : AppCompatActivity() {
    var listaPaises = hashMapOf<String, String>()
    var aciertos = 0
    var fallos = 0
    var i = 0
    var pais = ""
    var respuesta = ""
    var paises = 4
    lateinit var randomList: List<Int>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        labelEmpezar.setText(R.string.label_empezar)

        btnCambiar.setOnClickListener(){
            if (!capitales.isVisible){

                listaPaises = hashmap()

                val s: MutableSet<Int> = mutableSetOf()
                while (s.size < listaPaises.size) { s.add((0 until listaPaises.size).random()) }
                randomList = s.toList()
                println(randomList)

                capitales.setVisibility(View.VISIBLE)

                muestraInicio()
            }else{
                pasaSiguiente()
            }
        }
    }

    fun muestraInicio(){

        if (i != randomList.size){

            labelEmpezar.setText(R.string.label_cambiar)

            pais = listaPaises.keys.elementAt(randomList[i])
            txtPais.setText(pais)

            val s1: MutableSet<Int> = mutableSetOf()
            s1.add(i)
            while (s1.size < 4) { s1.add((0 until listaPaises.size).random()) }
            val randomList1 = s1.toList()

            val lista = arrayListOf(radioButton1, radioButton2, radioButton3, radioButton4)
            lista.shuffle()

            for (j in 0..3){
                lista.get(j).setText(listaPaises.values.elementAt(randomList[randomList1[j]]).toString())
            }

            i++

        }else{
            reiniciar()
        }
    }

    fun pasaSiguiente(){
        if (capitales.getCheckedRadioButtonId() == -1) {
            Toast(2)
        }else{
            checkAcierto()
            muestraInicio()
            capitales.clearCheck()
        }
    }

    fun checkAcierto(){
        val id: Int = capitales.checkedRadioButtonId
        val radio: RadioButton = findViewById(id)
        respuesta = radio.text.toString()
        if (respuesta.equals(listaPaises.get(pais))){
            aciertos++
        }else{
            fallos++
        }
    }

    fun reiniciar(){
        Toast(1)
        labelEmpezar.setText(R.string.label_empezar)
        txtPais.setText("")
        capitales.setVisibility(View.INVISIBLE)
        i=0
        aciertos=0
        fallos=0
    }
    fun Toast(i: Int){
        if (i == 1){
            val toastFin = Toast.makeText(applicationContext,"${listaPaises.size} paises, ${aciertos} aciertos / ${fallos} fallos", Toast.LENGTH_LONG)
            toastFin.setGravity(Gravity.TOP or Gravity.START,220,1450)
            toastFin.show()
        }
        if (i == 2){
            val toastNull = Toast.makeText(applicationContext,R.string.label_noCheck, Toast.LENGTH_SHORT)
            toastNull.setGravity(Gravity.TOP or Gravity.START,220,1450)
            toastNull.show()
        }
    }

    fun hashmap(): HashMap<String, String> {
        val listaPaises = hashMapOf<String, String>()

        if (paises>hashmap1().size){
            paises = hashmap1().size
        }
        if (paises<4){
            paises = 4
        }

        val s: MutableSet<Int> = mutableSetOf()
        while (s.size < paises) { s.add((0 until hashmap1().size).random()) }
        val rList = s.toList()

        var pais = ""

        for (i in 0..paises-1){
            pais = hashmap1().keys.elementAt(rList[i])
            listaPaises.put(pais, hashmap1().get(pais).toString())
        }

        return listaPaises
    }

    fun hashmap1(): HashMap<String, String> {
        val listaPaises = hashMapOf<String, String>(
                "Albania" to "Tirana",
                "Alemania" to "Berlín",
                "Andorra" to "Andorra La Vieja",
                "Armenia " to "Ereván",
                "Austria" to "Viena",
                "Azerbaiyán " to "Bakú",
                "Bélgica" to "Bruselas",
                "Bielorrusia" to "Minsk",
                "Bosnia y Herzegovina" to "Sarajevo",
                "Bulgaria" to "Sofía",
                "Chipre" to "Nicosia",
                "Ciudad del Vaticano" to "Ciudad del Vaticano",
                "Croacia" to "Zagreb",
                "Dinamarca" to "Copenhague",
                "Eslovaquia" to "Bratislava",
                "Eslovenia" to "Liubliana",
                "España" to "Madrid",
                "Estonia" to "Tallín",
                "Finlandia" to "Helsinki",
                "Francia" to "París",
                "Georgia" to "Tiflis",
                "Grecia" to "Atenas",
                "Hungría" to "Budapest",
                "Irlanda" to "Dublín",
                "Islandia" to "Reikiavik",
                "Italia" to "Roma",
                "Kazajistán" to "Nursultán",
                "Letonia" to "Riga",
                "Liechtenstein" to "Vaduz",
                "Lituania" to "Vilna",
                "Luxemburgo" to "Luxemburgo",
                "Macedonia del Norte" to "Skopie",
                "Malta" to "La Valeta",
                "Moldavia" to "Chisinau",
                "Mónaco" to "Mónaco",
                "Montenegro" to "Podgorica",
                "Noruega" to "Oslo",
                "Países Bajos" to "Ámsterdam",
                "Polonia" to "Varsovia",
                "Portugal" to "Lisboa",
                "República Checa" to "Praga",
                "Rumanía" to "Bucarest",
                "Rusia" to "Moscú",
                "San Marino" to "San Marino",
                "Serbia" to "Belgrado",
                "Suecia" to "Estocolmo",
                "Suiza" to "Berna",
                "Turquía" to "Ankara",
                "Ucrania" to "Kiev"
        )
        return listaPaises
    }
}
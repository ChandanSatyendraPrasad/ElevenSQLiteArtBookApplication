package chandan.satyendra.prasad.elevensqliteartbookapplication

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    lateinit var cursor:Cursor
    var  namevalueindex:Int = 0
    var  agevalueindex:Int = 0
    lateinit var sqlitedatabaseforbook:SQLiteDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        try {
            sqlitedatabaseforbook =
                this.openOrCreateDatabase("Artsbook", Context.MODE_PRIVATE, null)
            sqlitedatabaseforbook.execSQL("CREATE TABLE IF NOT EXISTS musicians(name VARCHAR,age INT(2))")
            //sqlitedatabaseforbook.execSQL("INSERT INTO musicians (name,age) VALUES ('Rita',52)")
            cursor = sqlitedatabaseforbook.rawQuery("SELECT * FROM musicians",null)
            println("Test :"+cursor)
            //showdata()
            updatedata()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun updatedata() {
        sqlitedatabaseforbook.execSQL("UPDATE musicians SET name = 'Rita Devi' WHERE  age =55 ")
        showdata()
    }

    private fun showdata() {
         namevalueindex=cursor.getColumnIndex("name")
         agevalueindex=cursor.getColumnIndex("age")

        cursor.moveToFirst()
        while (cursor!=null)
        {
            println("Name :"+cursor.getString(namevalueindex))
            println("Age :"+cursor.getString(agevalueindex))
            cursor.moveToNext()
        }
        cursor.close()
    }
}
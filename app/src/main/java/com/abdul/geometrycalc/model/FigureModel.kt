package com.abdul.geometrycalc.model

import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.room.*
import com.github.keelar.exprk.Expressions

@Entity(tableName = "figure")
class FigureModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo
    val name: String,
    @ColumnInfo
    @DrawableRes
    val img: Int
)

@Entity(tableName = "formula")
class FigureFormulas(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo
    val id_figure: Int,
    @ColumnInfo
    val name: String,
    @ColumnInfo
    val formula_dop_info: String,
    @ColumnInfo
    val exp: String,
    @ColumnInfo
    val laTex: String = "",
    @ColumnInfo
    @DrawableRes
    val img: Int,
    @ColumnInfo
    val extLaTex: String = "",
    @ColumnInfo
    val shareFormula: String = ""

) {
    @Ignore
    val fields = mutableListOf<Field>()

    @Ignore
    val regex = """\@\{[a-zA-Z]\}""".toRegex()
    @Ignore
    var tmpLatex:String = ""
    init {
        getArgs().forEach {
            fields.add(Field(it, "", "hint for $it","",""))
        }
    }

    fun getArgs(): Set<String> {
        return regex.findAll(exp).map { it.value }.toSet()
    }


    fun calc(): Float {
        var tmp = exp
        fields.forEach {
            tmp = tmp.replace(it.str, it.value)
        }
        val result = Expressions().eval(tmp)
        return result.toFloat()
    }
}

@Entity(tableName = "arguments")
class FormulaArguments(
    @ColumnInfo
    val id_formula: Int,
    @ColumnInfo
    val id_argument: Int,
    @ColumnInfo
    val symbol: String,
    @ColumnInfo
    val color: String,
    @ColumnInfo
    val hint: String

) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}


class Field(val str: String, var value: String, var hint: String, var symbol: String, var color: String) {}




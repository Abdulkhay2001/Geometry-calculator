package com.abdul.geometrycalc.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.abdul.geometrycalc.model.FormulaArguments
import com.abdul.geometrycalc.model.FigureFormulas
import com.abdul.geometrycalc.model.FigureModel

@Dao
interface Dao {
    @Query("SELECT * FROM figure")
    fun getAllFigure(): LiveData<List<FigureModel>>

//    @Query(
//        "SELECT * FROM figure" +
//                "INNER JOIN figure ON figure.id = id"
//    )
//    fun getAllFormuls(): List<FigureFolmuls>

    @Query("select * from formula where id_figure=:id")
    fun getFigureFormula(id: Int): LiveData<List<FigureFormulas>>

    @Query("select * from formula where id=:id")
    fun getFormulaDetails(id: Int): FigureFormulas

    @Query("select * from arguments where id_formula=:id")
    fun getFormulArgumrnt(id: Int): List<FormulaArguments>

    @Insert
    fun insertFigure(var1: FigureModel)

    @Insert
    fun insertFormula(var2: FigureFormulas)

    @Insert
    fun insertArgument(var3: FormulaArguments)
}
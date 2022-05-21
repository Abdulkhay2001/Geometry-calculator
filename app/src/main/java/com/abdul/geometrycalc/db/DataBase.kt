package com.abdul.geometrycalc.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.abdul.geometrycalc.R
import com.abdul.geometrycalc.model.FormulaArguments
import com.abdul.geometrycalc.model.FigureFormulas
import com.abdul.geometrycalc.model.FigureModel
import java.util.concurrent.Executors
import kotlin.math.tan

@Database(entities = [FigureModel::class, FigureFormulas::class, FormulaArguments::class], version = 1)
abstract class DataBase : RoomDatabase() {
    abstract fun figureDao(): Dao

    companion object {
        @Volatile private var INSTANCE: DataBase? = null

        fun getInstance(context: Context): DataBase {
            synchronized(this) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                        context,
                        DataBase::class.java,
                        "main.db"
                    )
                        .addCallback(object : RoomDatabase.Callback() {
                            override fun onCreate(db: SupportSQLiteDatabase) {
                                super.onCreate(db)

                                insertFirstData()
                            }
                        })
                        .allowMainThreadQueries()
                        .build()
                }
                return INSTANCE!!
            }
        }

        private fun insertFirstData() {
            Executors.newSingleThreadExecutor().execute {



                val square = FigureModel(1, "Квадрат",R.drawable.kvadrat)
                val konus = FigureModel(2,"Конус",R.drawable.konus)
                val krug = FigureModel(3,"Круг",R.drawable.krug)
                val kub = FigureModel(4,"Куб",R.drawable.kub)
                val paralelepiped = FigureModel(5,"паралелепипед",R.drawable.parallelepiped)
                val paralelogramm = FigureModel(6,"Параллелограмм",R.drawable.parallelogramm)
                val piramida = FigureModel(7,"Пирамида",R.drawable.piramida)
                val pravilniy_mnogougolnik = FigureModel(8,"Правильный многоугольник",R.drawable.prav_mnogougolnik)
                val prizma = FigureModel(9,"Призма",R.drawable.prizma)
                val prymougolnay_trapeciy = FigureModel(10,"Прямоугольныя трапеция",R.drawable.pramougolnay_trapeciy)
                val prymougolnik = FigureModel(11,"Прямоугольник",R.drawable.prymougolnic)
                INSTANCE!!.figureDao().insertFigure(square)
                INSTANCE!!.figureDao().insertFigure(konus)
                INSTANCE!!.figureDao().insertFigure(krug)
                INSTANCE!!.figureDao().insertFigure(kub)
                INSTANCE!!.figureDao().insertFigure(paralelepiped)
                INSTANCE!!.figureDao().insertFigure(paralelogramm)
                INSTANCE!!.figureDao().insertFigure(piramida)
                INSTANCE!!.figureDao().insertFigure(pravilniy_mnogougolnik)
                INSTANCE!!.figureDao().insertFigure(prizma)
                INSTANCE!!.figureDao().insertFigure(prymougolnay_trapeciy)
                INSTANCE!!.figureDao().insertFigure(prymougolnik)



                val sqrt_diagonal = FigureFormulas(1, 1, "Диагональ", "", "2^(1/2)*@{a}", "d=\\sqrt{2}a",R.drawable.kv_diagonal,"d=\\sqrt{2}*@{a}","d=sqrt(2)*a")
                val sqrt_perimetr = FigureFormulas(2,1,"Периметр","","4*@{a}","P=4a",R.drawable.kv_perimetr,"P=4*@{a}","P=4*a")
                val sqrt_ploshad1 = FigureFormulas(3,1,"Площадь","по стороне","@{a}^2","S=a^{2}",R.drawable.kv_ploshad_storona,"S=@{a}^{2}","S=a^2")
                val sqrt_ploshad2 = FigureFormulas(4,1,"Площадь","по диагонали","1/2*@{d}^2","S=\\frac{1}{2}*d^{2}",R.drawable.kv_ploshad_diagonal,"S=\\frac{1}{2}*@{d}^{2}","S=1/2*d^2")
                val sqrt_radius1 = FigureFormulas(5,1,"Радиус","по радиусу описаной окружности","@{a}/(2^(1/2))","R=\\frac{a}{\\sqrt{2}}",R.drawable.kv_radius_opis_okr,"R=\\frac{@{a}}{\\sqrt{2}}","R=a/sqrt(2)")
                val sqrt_radius2 = FigureFormulas(6,1,"Радиус","по радиусу вписаной окружности","@{a}/2","r=\\frac{a}{2}",R.drawable.kv_radius_vpis_okr,"r=\\frac{@{a}}{2}","r=a/2")
                INSTANCE!!.figureDao().insertFormula(sqrt_diagonal)
                INSTANCE!!.figureDao().insertFormula(sqrt_perimetr)
                INSTANCE!!.figureDao().insertFormula(sqrt_ploshad1)
                INSTANCE!!.figureDao().insertFormula(sqrt_ploshad2)
                INSTANCE!!.figureDao().insertFormula(sqrt_radius1)
                INSTANCE!!.figureDao().insertFormula(sqrt_radius2)

                val sqrt_diagonal_arg = FormulaArguments(1,1,"a","#388E3C","Сторона")
                val sqrt_perimetr_arg = FormulaArguments(2,1,"a","#388E3C","Сторона")
                val sqrt_ploshad1_arg = FormulaArguments(3,1,"a","#388E3C","Сторона")
                val sqrt_ploshad2_arg = FormulaArguments(4,1,"d","#388E3C","Диагональ")
                val sqrt_radius1_arg = FormulaArguments(5,1,"a","#388E3C","Сторона")
                val sqrt_radius2_arg = FormulaArguments(6,1,"a","#388E3C","Сторона")
                INSTANCE!!.figureDao().insertArgument(sqrt_diagonal_arg)
                INSTANCE!!.figureDao().insertArgument(sqrt_perimetr_arg)
                INSTANCE!!.figureDao().insertArgument(sqrt_ploshad1_arg)
                INSTANCE!!.figureDao().insertArgument(sqrt_ploshad2_arg)
                INSTANCE!!.figureDao().insertArgument(sqrt_radius1_arg)
                INSTANCE!!.figureDao().insertArgument(sqrt_radius2_arg)

                val konus_obrazuyshay = FigureFormulas(7,2,"Образующая","","@{h}^2+@{R}^2","L=h^{2}+R^{2}",R.drawable.konus_obrazuushay,"L=@{h}^{2}+@{R}^{2}","L=h^2+R^2")
                val konus_obem = FigureFormulas(8,2,"Оъбём","через высоту и радиус основания","1/3*3.14*@{R}^2*@{h}","V=\\frac{1}{3}*3.14*R^{2}*h",R.drawable.konus_obem,"V=\\frac{1}{3}*3.14*@{R}^{2}*@{h}","V=1/3*3.14+R^2*h")
                val konus_ploshad_poverhnosty = FigureFormulas(9,2,"Площадь поверхности","по радиусу и высоте","3.14*@{R}*(@{R}+(@{R}^2+@{h}^2)^(1/2))","S=3.14*R*(R+\\sqrt{R^{2}+h^{2}})",R.drawable.konus_plohsad_pov,"S=3.14*@{R}*(@{R}+\\sqrt{@{R}^{2}+@{h}^{2}})","S=3.14*R*(R+sqrt(R^2+h^2))")
                val konus_polnay_ploshad_poverhnosty = FigureFormulas(10,2,"Полная площадь поверхности","по радиусу и образующей","3.14*@{R}*(@{R}+@{L})","S=3.14*R(R+L)",R.drawable.konus_poln_ploshad_pov,"S=3.14*@{R}*(@{R}+@{L})","S=3.14*R*(R+L)")
                INSTANCE!!.figureDao().insertFormula(konus_obrazuyshay)
                INSTANCE!!.figureDao().insertFormula(konus_obem)
                INSTANCE!!.figureDao().insertFormula(konus_ploshad_poverhnosty)
                INSTANCE!!.figureDao().insertFormula(konus_polnay_ploshad_poverhnosty)

                val konus_obrazuyshay_arg = FormulaArguments(7,1,"h","#388E3C","Высота")
                val konus_obrazuyshay_arg2 = FormulaArguments(7,2,"R","#388E3C","Радиус")
                val konus_obem_arg = FormulaArguments(8,1,"R","#388E3C","Радиус")
                val konus_obem_arg2 = FormulaArguments(8,2,"h","#388E3C","Высота")
                val konus_ploshad_poverhnosty_arg = FormulaArguments(9,1,"R","#388E3C","Радиус")
                val konus_ploshad_poverhnosty_arg2 = FormulaArguments(9,2,"h","#388E3C","Высота")
                val konus_polnay_ploshad_poverhnosty_arg = FormulaArguments(10,1,"R","#388E3C","Радиус")
                val konus_polnay_ploshad_poverhnosty_arg2 = FormulaArguments(10,2,"L","#388E3C","Образующая")
                INSTANCE!!.figureDao().insertArgument(konus_obrazuyshay_arg)
                INSTANCE!!.figureDao().insertArgument(konus_obrazuyshay_arg2)
                INSTANCE!!.figureDao().insertArgument(konus_obem_arg)
                INSTANCE!!.figureDao().insertArgument(konus_obem_arg2)
                INSTANCE!!.figureDao().insertArgument(konus_ploshad_poverhnosty_arg)
                INSTANCE!!.figureDao().insertArgument(konus_ploshad_poverhnosty_arg2)
                INSTANCE!!.figureDao().insertArgument(konus_polnay_ploshad_poverhnosty_arg)
                INSTANCE!!.figureDao().insertArgument(konus_polnay_ploshad_poverhnosty_arg2)

                val krug_diametr = FigureFormulas(11,3,"Диаметр","","2*@{r}","d=2r",R.drawable.krug_diametr,"d=2*@{r}","d=2*r")
                val krug_dlina_kruga1 = FigureFormulas(12,3,"Длина круга","через радиусрадиус","2*3.14*@{r}","l=2*3.14*r",R.drawable.krug_dlina_radius,"l=2*3.14*@{r}","l=2*3.14*r")
                val krug_dlina_kruga2 = FigureFormulas(13,3,"Длина круга","через диаметр","3.14*@{d}","l=3.14*d",R.drawable.krug_dlina_diametr,"l=3.14*@{d}","l=3.14*d")
                val krug_radius = FigureFormulas(16,3,"Радиус","","@{d}/2","r=\\frac{d}{2}",R.drawable.krug_radius,"r=\\frac{@{d}}{2}","r=d/2")

                INSTANCE!!.figureDao().insertFormula(krug_diametr)
                INSTANCE!!.figureDao().insertFormula(krug_dlina_kruga1)
                INSTANCE!!.figureDao().insertFormula(krug_dlina_kruga2)
                INSTANCE!!.figureDao().insertFormula(krug_radius)

                val krug_diametr_arg = FormulaArguments(11,1,"r","#388E3C","Радиус")
                val krug_dlina_kruga1_arg = FormulaArguments(12,1,"r","#388E3C","Радиус")
                val krug_dlina_kruga2_arg = FormulaArguments(13,1,"d","#388E3C","Диаметр")
                val krug_radius_arg = FormulaArguments(16,1,"d","#388E3C","Диаметр")
                INSTANCE!!.figureDao().insertArgument(krug_diametr_arg)
                INSTANCE!!.figureDao().insertArgument(krug_dlina_kruga1_arg)
                INSTANCE!!.figureDao().insertArgument(krug_dlina_kruga2_arg)
                INSTANCE!!.figureDao().insertArgument(krug_radius_arg)



                val kub_diagonal = FigureFormulas(17,4,"Диагональ","","(3*@{a})^(1/2)","d=\\sqrt{3a}",R.drawable.kub_diagonal,"d=\\sqrt{3*@{a}}","d=sqrt(3*a)")
                val kub_obem = FigureFormulas(18,4,"Объём","через сторону","@{a}^3","V=a^{3}",R.drawable.kub_obem,"V=@{a}^{3}","V=a^3")
                val kub_ploshad_poverhnosty = FigureFormulas(19,4,"Площадь поверхности","","6*@{a}^2","S=6a^2",R.drawable.kub_ploshad,"S=6*@{a}^2","S=6*a^2")
                val kub_storona = FigureFormulas(20,4,"Сторона","","(@{S}/6)^(1/2)","a=\\sqrt{\\frac{S}{6}}",R.drawable.kub_storona,"a=\\sqrt{\\frac{@{S}}{6}}","a=sqrt(S/6)")
                INSTANCE!!.figureDao().insertFormula(kub_diagonal)
                INSTANCE!!.figureDao().insertFormula(kub_obem)
                INSTANCE!!.figureDao().insertFormula(kub_ploshad_poverhnosty)
                INSTANCE!!.figureDao().insertFormula(kub_storona)

                val kub_diagonalr_arg = FormulaArguments(17,1,"a","#388E3C","Ребро")
                val kub_obem_arg = FormulaArguments(18,1,"a","#388E3C","Ребро")
                val kub_ploshad_poverhnosty_arg = FormulaArguments(19,1,"a","#388E3C","Сторона")
                val kub_storona_arg = FormulaArguments(20,1,"S","#388E3C","Площадь")
                INSTANCE!!.figureDao().insertArgument(kub_diagonalr_arg)
                INSTANCE!!.figureDao().insertArgument(kub_obem_arg)
                INSTANCE!!.figureDao().insertArgument(kub_ploshad_poverhnosty_arg)
                INSTANCE!!.figureDao().insertArgument(kub_storona_arg)


                val paralelepiped_diagonal = FigureFormulas(21,5,"Диагональ","прямоугольного параллелепипеда","(@{a}^2+@{b}^2+@{c}^2)^(1/2)","d=\\sqrt{a^{2}+b^{2}+c^{2}}",R.drawable.paralilepiped_diametr,"d=\\sqrt{@{a}^{2}+@{b}^{2}+@{c}^{2}}","d=sqrt(a^2+b^2+c^2)")
                val paralelepiped_obem = FigureFormulas(22,5,"Объём","через длины основания и высоты","@{a}*@{b}*@{c}","V=a*b*c",R.drawable.paralilepiped_obem,"V=@{a}*@{b}*@{c}","V=a*b*c")
                val paralelepiped_ploshad_poverhnosty = FigureFormulas(23,5,"Площадь поверхности","","2*(@{a}*@{b}+@{b}*@{c}+@{a}*@{c})","S=2(a*b+b*c+a*c)",R.drawable.paralilepiped_ploshad,"S=2*(@{a}*@{b}+@{b}*@{c}+@{a}*@{c})","S=2*(a*b+b*c+a*c)")
                val paralelepiped_storona = FigureFormulas(24,5,"Сторона","по двум рёбрам и полной площади","(@{S}-2*@{b}*@{c})/(2*(@{b}+@{c}))","a=\\frac{S-2*b*c}{2(b+c)}",R.drawable.paralilepiped_storona,"a=\\frac{@{S}-2*@{b}*@{c}}{2(@{b}+@{c})}","a=(S-2*b*c)/(2(b+c))")
                INSTANCE!!.figureDao().insertFormula(paralelepiped_diagonal)
                INSTANCE!!.figureDao().insertFormula(paralelepiped_obem)
                INSTANCE!!.figureDao().insertFormula(paralelepiped_ploshad_poverhnosty)
                INSTANCE!!.figureDao().insertFormula(paralelepiped_storona)

                val paralelepiped_diagonal_arg = FormulaArguments(21,1,"a","#388E3C","Ребро")
                val paralelepiped_diagonal_arg2 = FormulaArguments(21,2,"b","#388E3C","Ребро")
                val paralelepiped_diagonal_arg3 = FormulaArguments(21,3,"c","#388E3C","Ребро")
                val paralelepiped_obem_arg = FormulaArguments(22,1,"a","#388E3C","Ребро")
                val paralelepiped_obem_arg2 = FormulaArguments(22,2,"b","#388E3C","Ребро")
                val paralelepiped_obem_arg3 = FormulaArguments(22,3,"c","#388E3C","Ребро")
                val paralelepiped_ploshad_poverhnosty_arg = FormulaArguments(23,1,"a","#388E3C","Ребро")
                val paralelepiped_ploshad_poverhnosty_arg2 = FormulaArguments(23,1,"b","#388E3C","Ребро")
                val paralelepiped_ploshad_poverhnosty_arg3 = FormulaArguments(23,1,"c","#388E3C","Ребро")
                val paralelepiped_storona_arg = FormulaArguments(24,1,"S","#388E3C","Полная площадь")
                val paralelepiped_storona_arg2 = FormulaArguments(24,1,"b","#388E3C","Ребро")
                val paralelepiped_storona_arg3 = FormulaArguments(24,1,"c","#388E3C","Ребро")
                INSTANCE!!.figureDao().insertArgument(paralelepiped_diagonal_arg)
                INSTANCE!!.figureDao().insertArgument(paralelepiped_diagonal_arg2)
                INSTANCE!!.figureDao().insertArgument(paralelepiped_diagonal_arg3)
                INSTANCE!!.figureDao().insertArgument(paralelepiped_obem_arg)
                INSTANCE!!.figureDao().insertArgument(paralelepiped_obem_arg2)
                INSTANCE!!.figureDao().insertArgument(paralelepiped_obem_arg3)
                INSTANCE!!.figureDao().insertArgument(paralelepiped_ploshad_poverhnosty_arg)
                INSTANCE!!.figureDao().insertArgument(paralelepiped_ploshad_poverhnosty_arg2)
                INSTANCE!!.figureDao().insertArgument(paralelepiped_ploshad_poverhnosty_arg3)
                INSTANCE!!.figureDao().insertArgument(paralelepiped_storona_arg)
                INSTANCE!!.figureDao().insertArgument(paralelepiped_storona_arg2)
                INSTANCE!!.figureDao().insertArgument(paralelepiped_storona_arg3)

                val paralelogram_diagonal = FigureFormulas(25,6,"Диагональ","","(2*@{S})/(@{D}*sin(@{a}))","d=\\frac{2S}{D*sin(\\alpha)}",R.drawable.paralelogramm_diagonal,"d=\\frac{2*@{S}}{@{D}*sin(@{a})}")//???? не работает sin(a)
                val paralelogram_perimetr = FigureFormulas(26,6,"Периметр","","2*@{a}+2*@{b}","P=2a+2b",R.drawable.paralelogramm_perimetr,"P=2*@{a}+2*@{b}")
                val paralelogram_ploshad1 = FigureFormulas(27,6,"Площадь","по стороне и высоте","@{a}*@{h}","S=a*h",R.drawable.paralelogramm_ploshad_storona,"S=@{a}*@{h}")
                val paralelogram_ploshad2 = FigureFormulas(28,6,"Площадь","по двум сторонам и углу между ними","@{a}*@{b}*sin(@{c})","S=a*b*sin(\\alpha)",R.drawable.paralelogramm_ploshad_po_dvum_storonam,"S=@{a}*@{b}*sin(@{c})") //????
                val paralelogram_ploshad3 = FigureFormulas(29,6,"Площадь","по двум диагоналям и углу между ними","1/2*@{D}*@{d}*sin(@{a})","S=\\frac{1}{2}*D*d*sin(\\alpha)",R.drawable.paralelogramm_ploshad_po_diagonalam,"S=\\frac{1}{2}*@{D}*@{d}*sin(@{a})")
                val paralelogram_storona = FigureFormulas(30,6,"Сторона","","@{h}/sin(@{a})","a=\\frac{h}{sin(\\alpha)}",R.drawable.paralelpgramm_storona,"a=\\frac{@{h}}{sin(@{a})}")
                INSTANCE!!.figureDao().insertFormula(paralelogram_diagonal)
                INSTANCE!!.figureDao().insertFormula(paralelogram_perimetr)
                INSTANCE!!.figureDao().insertFormula(paralelogram_ploshad1)
                INSTANCE!!.figureDao().insertFormula(paralelogram_ploshad2)
                INSTANCE!!.figureDao().insertFormula(paralelogram_ploshad3)
                INSTANCE!!.figureDao().insertFormula(paralelogram_storona)

                val paralelogram_diagonal_arg = FormulaArguments(25,1,"S","#388E3C","Площадь")
                val paralelogram_diagonal_arg2 = FormulaArguments(25,2,"D","#388E3C","Большая диагональ")
                val paralelogram_diagonal_arg3 = FormulaArguments(25,3,"a","#388E3C","Угол(в градусах)")
                val paralelogram_perimetr_arg = FormulaArguments(26,1,"a","#388E3C","Сторона")
                val paralelogram_perimetr_arg2 = FormulaArguments(26,2,"b","#388E3C","Сторона")
                val paralelogram_ploshad1_arg = FormulaArguments(27,1,"a","#388E3C","Сторона")
                val paralelogram_ploshad1_arg2 = FormulaArguments(27,2,"h","#388E3C","Высота")
                val paralelogram_ploshad2_arg = FormulaArguments(28,1,"a","#388E3C","Сторона")
                val paralelogram_ploshad2_arg2 = FormulaArguments(28,2,"b","#388E3C","Сторона")
                val paralelogram_ploshad2_arg3 = FormulaArguments(28,3,"a","#388E3C","Угол(в градусах)")
                val paralelogram_ploshad3_arg = FormulaArguments(29,1,"D","#388E3C","Большая диагональ")
                val paralelogram_ploshad3_arg2 = FormulaArguments(29,2,"d","#388E3C","Диагональ")
                val paralelogram_ploshad3_arg3 = FormulaArguments(29,3,"a","#388E3C","Угол(в градусах)")
                val paralelogram_storona_arg = FormulaArguments(30,1,"h","#388E3C","Высота")
                val paralelogram_storona_arg2 = FormulaArguments(30,2,"a","#388E3C","Угол(в градусах)")
                INSTANCE!!.figureDao().insertArgument(paralelogram_diagonal_arg)
                INSTANCE!!.figureDao().insertArgument(paralelogram_diagonal_arg2)
                INSTANCE!!.figureDao().insertArgument(paralelogram_diagonal_arg3)
                INSTANCE!!.figureDao().insertArgument(paralelogram_perimetr_arg)
                INSTANCE!!.figureDao().insertArgument(paralelogram_perimetr_arg2)
                INSTANCE!!.figureDao().insertArgument(paralelogram_ploshad1_arg)
                INSTANCE!!.figureDao().insertArgument(paralelogram_ploshad1_arg2)
                INSTANCE!!.figureDao().insertArgument(paralelogram_ploshad2_arg)
                INSTANCE!!.figureDao().insertArgument(paralelogram_ploshad2_arg2)
                INSTANCE!!.figureDao().insertArgument(paralelogram_ploshad2_arg3)
                INSTANCE!!.figureDao().insertArgument(paralelogram_ploshad3_arg)
                INSTANCE!!.figureDao().insertArgument(paralelogram_ploshad3_arg2)
                INSTANCE!!.figureDao().insertArgument(paralelogram_ploshad3_arg3)
                INSTANCE!!.figureDao().insertArgument(paralelogram_storona_arg)
                INSTANCE!!.figureDao().insertArgument(paralelogram_storona_arg2)

                val piramida_apophema = FigureFormulas(31,7,"Апофема","правильной пирамиды","sqrt(@{h}^2+(@{a}/(2*tan(180/@{n})))^2)","L=\\sqrt{h^{2}+\\frac{a}{2tan(180/n)}}",R.drawable.piramida_apophema,"L=\\sqrt{@{h}^{2}+\\frac{@{a}}{2*tan(180/@{n})}}")//????
                val piramida_obem = FigureFormulas(32,7,"Объём","через площадь основания и высоту","1/3*@{S}*@{h}","V=\\frac{1}{3}S*h",R.drawable.piramida_obem,"V=\\frac{1}{3}*@{S}*@{prymh}")
                val piramida_ploshad = FigureFormulas(33,7,"Площадь","","@{a}^2+2*@{a}*@{L}","S=a^{2}+2a*L",R.drawable.piramida_ploshad,"S=@{a}^{2}+2*@{a}*@{L}")
                val piramida_rebro = FigureFormulas(34,7,"Ребро","","sqrt(@{h}^2+@{a}/(2*sin(180/@{n})))","b=\\sqrt{h^{2}+\\frac{a}{2sin(180/n)}}",R.drawable.piramida_rebro,"b=\\sqrt{@{h}^{2}+\\frac{@{a}}{2*sin(180/@{n})}}")
                INSTANCE!!.figureDao().insertFormula(piramida_apophema)
                INSTANCE!!.figureDao().insertFormula(piramida_obem)
                INSTANCE!!.figureDao().insertFormula(piramida_ploshad)
                INSTANCE!!.figureDao().insertFormula(piramida_rebro)

                val piramida_apophema_arg = FormulaArguments(31,1,"h","#388E3C","Высота")
                val piramida_apophema_arg2 = FormulaArguments(31,2,"a","#388E3C","Сторона")
                val piramida_apophema_arg3 = FormulaArguments(31,3,"n","#388E3C","Число сторон основания")
                val piramida_obem_arg = FormulaArguments(32,1,"S","#388E3C","Площадь")
                val piramida_obem_arg2 = FormulaArguments(32,2,"h","#388E3C","Высота")
                val piramida_ploshad_arg = FormulaArguments(33,1,"a","#388E3C","Сторона")
                val piramida_ploshad_arg2 = FormulaArguments(33,2,"L","#388E3C","Апофема")
                val piramida_rebro_arg = FormulaArguments(34,1,"h","#388E3C","Высота")
                val piramida_rebro_arg2 = FormulaArguments(34,2,"a","#388E3C","Сторона")
                val piramida_rebro_arg3 = FormulaArguments(34,3,"n","#388E3C","Число сторон")
                INSTANCE!!.figureDao().insertArgument(piramida_apophema_arg)
                INSTANCE!!.figureDao().insertArgument(piramida_apophema_arg2)
                INSTANCE!!.figureDao().insertArgument(piramida_apophema_arg3)
                INSTANCE!!.figureDao().insertArgument(piramida_obem_arg)
                INSTANCE!!.figureDao().insertArgument(piramida_obem_arg2)
                INSTANCE!!.figureDao().insertArgument(piramida_ploshad_arg)
                INSTANCE!!.figureDao().insertArgument(piramida_ploshad_arg2)
                INSTANCE!!.figureDao().insertArgument(piramida_rebro_arg)
                INSTANCE!!.figureDao().insertArgument(piramida_rebro_arg2)
                INSTANCE!!.figureDao().insertArgument(piramida_rebro_arg3)


                val pravilniy_mnogougolnik_ploshad = FigureFormulas(35,8,"Площадь","по стороне","(@{n}*@{a}^2)/(4*tan(180/@{n}))","S=\\frac{n*a^{2}}{4*tan(180/n)}",R.drawable.mnogougolnik_ploshad,"S=\\frac{@{n}*@{a}^{2}}{4*tan(180/@{n})}")//????
                val pravilniy_mnogougolnik_ploshad2 = FigureFormulas(36,8,"Площадь","по радиусу описаной окружности","1/2*@{R}^2*@{n}*sin(360/@{n})","S=\\frac{1}{2}*R^{2}*n*sin(360/n)",R.drawable.mnogougolnik__ploshad_2,"S=\\frac{1}{2}*@{R}^{2}*@{n}*sin(360/@{n})")//????
                val pravilniy_mnogougolnik_ploshad3 = FigureFormulas(37,8,"Площадь","по радиусу вписаной окружности","@{r}^2*@{n}*tan(180/@{n})","S=r^{2}*n*tan(180/n)",R.drawable.mnogougolnik_ploshad3,"S=@{r}^{2}*@{n}*tan(180/@{n})")//????
                val pravilniy_mnogougolnik_radius = FigureFormulas(38,8,"Радиус вписаной окружности","","@{a}/(2*tan(180/@{n}))","r=\\frac{a}{2tan(180/n)}",R.drawable.mnogougolnk_vpisanoy_okr,"r=\\frac{@{a}}{2*tan(180/@{n})}")//????
                val pravilniy_mnogougolnik_radius2 = FigureFormulas(39,8,"Радиус описаной окружности","","a/(2*sin(180/@{n}))","R=\\frac{a}{2sin(180/n)}",R.drawable.mnogougolnik_opisanoy_okr,"R=\\frac{@{a}}{2*sin(180/@{n})}")//????
                INSTANCE!!.figureDao().insertFormula(pravilniy_mnogougolnik_ploshad)
                INSTANCE!!.figureDao().insertFormula(pravilniy_mnogougolnik_ploshad2)
                INSTANCE!!.figureDao().insertFormula(pravilniy_mnogougolnik_ploshad3)
                INSTANCE!!.figureDao().insertFormula(pravilniy_mnogougolnik_radius)
                INSTANCE!!.figureDao().insertFormula(pravilniy_mnogougolnik_radius2)

                val pravilniy_mnogougolnik_ploshad_arg = FormulaArguments(35,1,"n","#388E3C","Число сторон")
                val pravilniy_mnogougolnik_ploshad_arg2 = FormulaArguments(35,2,"a","#388E3C","Сторона")
                val pravilniy_mnogougolnik_ploshad2_arg = FormulaArguments(36,1,"n","#388E3C","Число сторон")
                val pravilniy_mnogougolnik_ploshad2_arg2 = FormulaArguments(36,2,"R","#388E3C","Радиус")
                val pravilniy_mnogougolnik_ploshad3_arg = FormulaArguments(37,1,"n","#388E3C","Число сторон")
                val pravilniy_mnogougolnik_ploshad3_arg2 = FormulaArguments(37,2,"r","#388E3C","Радиус")
                val pravilniy_mnogougolnik_radius_arg = FormulaArguments(38,1,"a","#388E3C","Сторона")
                val pravilniy_mnogougolnik_radius_arg2 = FormulaArguments(38,2,"n","#388E3C","Количество чторон")
                val pravilniy_mnogougolnik_radius2_arg = FormulaArguments(39,1,"a","#388E3C","Сторона")
                val pravilniy_mnogougolnik_radius2_arg2 = FormulaArguments(39,2,"n","#388E3C","Количество чторон")
                INSTANCE!!.figureDao().insertArgument(pravilniy_mnogougolnik_ploshad_arg)
                INSTANCE!!.figureDao().insertArgument(pravilniy_mnogougolnik_ploshad_arg2)
                INSTANCE!!.figureDao().insertArgument(pravilniy_mnogougolnik_ploshad2_arg)
                INSTANCE!!.figureDao().insertArgument(pravilniy_mnogougolnik_ploshad2_arg2)
                INSTANCE!!.figureDao().insertArgument(pravilniy_mnogougolnik_ploshad3_arg)
                INSTANCE!!.figureDao().insertArgument(pravilniy_mnogougolnik_ploshad3_arg2)
                INSTANCE!!.figureDao().insertArgument(pravilniy_mnogougolnik_radius_arg)
                INSTANCE!!.figureDao().insertArgument(pravilniy_mnogougolnik_radius_arg2)
                INSTANCE!!.figureDao().insertArgument(pravilniy_mnogougolnik_radius2_arg)
                INSTANCE!!.figureDao().insertArgument(pravilniy_mnogougolnik_radius2_arg2)

                val prizma_obem = FigureFormulas(40,9,"Объём","","@{S}*@{h}","V=S*h",R.drawable.prizma_obem,"V=@{S}*@{h}")
                INSTANCE!!.figureDao().insertFormula(prizma_obem)

                val prizma_obem_arg = FormulaArguments(40,1,"S","#388E3C","Площадь")
                val prizma_obem_arg2 = FormulaArguments(40,2,"h","#388E3C","Высота")
                INSTANCE!!.figureDao().insertArgument(prizma_obem_arg)
                INSTANCE!!.figureDao().insertArgument(prizma_obem_arg2)

                val prymougolnay_trapeciy_diagonal = FigureFormulas(41,10,"Диагональ","","sqrt(@{c}^2+@{b}^2)","d=\\sqrt{c^{2}+b^{2}}",R.drawable.prymougolnay_trapecia_diagonal,"d=\\sqrt{@{c}^{2}+@{b}^{2}}")
                INSTANCE!!.figureDao().insertFormula(prymougolnay_trapeciy_diagonal)

                val prymougolnay_trapeciy_diagonal_arg = FormulaArguments(41,1,"b","#388E3C","Верхнее основание")
                val prymougolnay_trapeciy_diagonal_arg2 = FormulaArguments(41,2,"c","#388E3C","Боковая сторона")
                INSTANCE!!.figureDao().insertArgument(prymougolnay_trapeciy_diagonal_arg)
                INSTANCE!!.figureDao().insertArgument(prymougolnay_trapeciy_diagonal_arg2)


                val prymougolnik_diagonal = FigureFormulas(42,11,"Диагональ","","@{a}/sin(@{q})","d=\\frac{a}{sin(\\alpha)}",R.drawable.prymougolnik_diagonal,"d=\\frac{@{a}}{sin(@{q})}")
                val prymougolnik_perimetr = FigureFormulas(43,11,"Периметр","","2*(@{a}+@{b})","p=2*(a+b)",R.drawable.prymougolnik_perimetr,"P=2*(@{a}+@{b})")
                val prymougolnik_ploshad = FigureFormulas(44,11,"Площадь","через стороны","@{a}*@{b}","S=a*b",R.drawable.prymougolnik_ploshad,"S=@{a}*@{b}")
                val prymougolnik_ploshad2 = FigureFormulas(45,11,"Площадь","через диагонали и углу между ними","1/2*@{d}^2*sin(@{a})","S=\\frac{1}{2}*d^{2}*sin(\\alpha)",R.drawable.prymougolnik_ploshad2,"S=\\frac{1}{2}*@{d}^{2}*sin(@{a})")
                val prymougolnik_radius = FigureFormulas(46,11,"Радиус","описаной окружности","((@{a}^2+@{b}^2)^(1/2))/2","R=\\frac{\\sqrt{a^{2}+b^{2}}}{2}",R.drawable.prymougolnik_radius,"R=\\frac{\\sqrt{@{a}^{2}+@{b}^{2}}}{2}")
                val prymougolnik_storona = FigureFormulas(47,11,"Сторона","","(@{d}^2-@{a}^2)^(1/2)","b=\\sqrt{d^{2}-a^{2}}",R.drawable.prymougolnik_storona,"b=\\sqrt{@{d}^{2}-@{a}^{2}}")
                INSTANCE!!.figureDao().insertFormula(prymougolnik_diagonal)
                INSTANCE!!.figureDao().insertFormula(prymougolnik_perimetr)
                INSTANCE!!.figureDao().insertFormula(prymougolnik_ploshad)
                INSTANCE!!.figureDao().insertFormula(prymougolnik_ploshad2)
                INSTANCE!!.figureDao().insertFormula(prymougolnik_radius)
                INSTANCE!!.figureDao().insertFormula(prymougolnik_storona)

                val prymougolnik_diagonal_arg = FormulaArguments(42,1,"a","#388E3C","Сторона")
                val prymougolnik_diagonal_arg2 = FormulaArguments(42,2,"a","#388E3C","Угол (в градусах)")
                val prymougolnik_perimetr_arg = FormulaArguments(43,1,"a","#388E3C","Сторона")
                val prymougolnik_perimetr_arg2 = FormulaArguments(43,2,"b","#388E3C","Сторона")
                val prymougolnik_ploshad_arg = FormulaArguments(44,1,"a","#388E3C","Сторона")
                val prymougolnik_ploshad_arg2 = FormulaArguments(44,2,"b","#388E3C","Сторона")
                val prymougolnik_ploshad2_arg = FormulaArguments(45,1,"d","#388E3C","Диагональ")
                val prymougolnik_ploshad2_arg2 = FormulaArguments(45,2,"a","#388E3C","Угол (в градусах)")
                val prymougolnik_radius_arg = FormulaArguments(46,1,"a","#388E3C","Сторона")
                val prymougolnik_radius_arg2 = FormulaArguments(46,2,"b","#388E3C","Сторона")
                val prymougolnik_storona_arg = FormulaArguments(47,1,"d","#388E3C","Диагональ")
                val prymougolnik_storona_arg2 = FormulaArguments(47,2,"a","#388E3C","Сторона")
                INSTANCE!!.figureDao().insertArgument(prymougolnik_diagonal_arg)
                INSTANCE!!.figureDao().insertArgument(prymougolnik_diagonal_arg2)
                INSTANCE!!.figureDao().insertArgument(prymougolnik_perimetr_arg)
                INSTANCE!!.figureDao().insertArgument(prymougolnik_perimetr_arg2)
                INSTANCE!!.figureDao().insertArgument(prymougolnik_ploshad_arg)
                INSTANCE!!.figureDao().insertArgument(prymougolnik_ploshad_arg2)
                INSTANCE!!.figureDao().insertArgument(prymougolnik_ploshad2_arg)
                INSTANCE!!.figureDao().insertArgument(prymougolnik_ploshad2_arg2)
                INSTANCE!!.figureDao().insertArgument(prymougolnik_radius_arg)
                INSTANCE!!.figureDao().insertArgument(prymougolnik_radius_arg2)
                INSTANCE!!.figureDao().insertArgument(prymougolnik_storona_arg)
                INSTANCE!!.figureDao().insertArgument(prymougolnik_storona_arg2)


            }

        }


    }


}



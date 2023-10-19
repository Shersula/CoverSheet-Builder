package com.coversheet;

import java.sql.*;
import java.util.HashMap;
import java.util.Date;
import java.util.GregorianCalendar;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.export.SimpleOdsExporterConfiguration;
import net.sf.jasperreports.export.SimpleOdsReportConfiguration;
import net.sf.jasperreports.export.SimpleOdtExporterConfiguration;
import net.sf.jasperreports.export.SimpleOdtReportConfiguration;
import net.sf.jasperreports.export.SimpleXlsExporterConfiguration;
import net.sf.jasperreports.export.SimpleXlsReportConfiguration;

public class App 
{
    private static int GetRowCount(ResultSet rs) throws SQLException {
        if(!rs.next()) System.out.println("В ResultSet нет данных");
        rs.beforeFirst();
        int count = 0;
        while(rs.next())
        {
            count++;
        }
        rs.beforeFirst();
        return count;
    }

    public static void main( String[] args )
    {
        Connection DataBase;
        try
        {
/////////////////////////////////////////////////////////////////SQL CONNECT CODE/////////////////////////////////////////////////////////////////////////////////////////////////
            
            DataBase = DriverManager.getConnection("jdbc:postgresql://26.139.88.129:5432/postgres", "postgres", "");
            System.out.println("База данных подключена");
            Statement sql = DataBase.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

/////////////////////////////////////////////////////////////////SQL QUERY CODE///////////////////////////////////////////////////////////////////////////////////////////////////
            
            ResultSet res = sql.executeQuery("SELECT \"coversheet\".\"RemovedPlates\".\"Operation\" AS \"RemoveOperation\"," +
                    "\"coversheet\".\"RemovedPlates\".\"Count\" AS \"RemoveCount\"," +
                    "\"coversheet\".\"RemovedPlates\".\"Number\"," +
                    "\"coversheet\".\"RemovedPlates\".\"Defects\"," +
                    "\"coversheet\".\"DeliveredPlates\".\"Operation\" AS \"DeliveredOperation\"," +
                    "\"coversheet\".\"DeliveredPlates\".\"Count\" AS \"DeliveredCount\"," +
                    "\"coversheet\".\"DeliveredPlates\".\"OriginalBatch\"," +
                    "\"coversheet\".\"DeliveredPlates\".\"OTK\"," +
                    "\"coversheet\".\"ControlPlates\".\"Sign\"," +
                    "\"coversheet\".\"ControlPlates\".\"Stamp\"" +
                    "FROM \"coversheet\".\"RemovedPlates\"" +
                    "INNER JOIN \"coversheet\".\"DeliveredPlates\" ON \"coversheet\".\"RemovedPlates\".\"Number\" = \"coversheet\".\"DeliveredPlates\".\"Number\"" +
                    "INNER JOIN \"coversheet\".\"ControlPlates\" ON \"coversheet\".\"RemovedPlates\".\"Number\" = \"coversheet\".\"ControlPlates\".\"Number\""); //Запрос к базе данных

            int RowCount = GetRowCount(res); // Количество строк полученные от базы данных

            System.out.println("Найдено " + RowCount + " уникальных номеров");
/////////////////////////////////////////////////////////////////DATA PREPARE CODE/////////////////////////////////////////////////////////////////////////////////////////////////
            
            DataCollectionCreator Collection = new DataCollectionCreator(); //Объект для сборки данных в таблицу
            for(int i = 0; i  < RowCount; i++)
            {
                res.next();
                Collection.add(new DataBeanCoverSheet(res.getInt("Number"), res.getInt("RemoveOperation"), res.getInt("RemoveCount"), res.getString("Defects"),
                res.getInt("DeliveredOperation"), res.getInt("DeliveredCount"), res.getInt("OriginalBatch"), res.getInt("OTK"),
                res.getString("Sign"), res.getString("Stamp")));
            }

/////////////////////////////////////////////////////////////////REPORT COMPILER CODE////////////////////////////////////////////////////////////////////////////////////////////////

            JasperReport Report = JasperCompileManager.compileReport("demo\\src\\main\\resources\\CoverSheet.jrxml");

/////////////////////////////////////////////////////////////////DATA FILL CODE///////////////////////////////////////////////////////////////////////////////////////////////////////

            HashMap<String, Object> Parametrs = new HashMap<String, Object>(); //Хэш карта с параметрами для таблицы
            Parametrs.put("ListNumber", 122); //Параметр тип:Integer - Номер маршрутного листа

            Parametrs.put("Date", new Date()); //Параметр тип:java.util.Date - Текущая дата(Парсится в самом отчете)

            Parametrs.put("Workshop", new String("Тестовый отдел")); //Параметр тип:String - Название отдела

            Parametrs.put("Product", new String("Тестовое изделие")); //Параметр тип:String - Название изделия

            Parametrs.put("BatchNumber", 123); //Параметр тип:Integer - Номер партии

            Parametrs.put("RouteNumber", 2223); //Параметр тип:Integer - Номер маршрута

            Parametrs.put("CDPlate", new String("Тест")); //Параметр тип:String - Обозначение КД

            Parametrs.put("SourceBatchNumber", 101); //Параметр тип:Integer - Номер партии исходных пластин

            Parametrs.put("PassportNumber", 684732652); //Параметр тип:Integer - Номер паспорта

            Parametrs.put("LeadingTechnologist", new String("Ильина Галина Викторовна")); //Параметр тип:String - Ведущий технолог

            Parametrs.put("WorkingPlateNumbers1", 78967); //Параметр тип:Integer - Номера рабочих пластин
            Parametrs.put("WorkingPlateNumbers2", 12423); //Параметр тип:Integer - Номера рабочих пластин
            Parametrs.put("WorkingPlateNumbers3", 654645); //Параметр тип:Integer - Номера рабочих пластин
            Parametrs.put("WorkingPlateNumbers4", 124443); //Параметр тип:Integer - Номера рабочих пластин

            Parametrs.put("DatePresentation", new GregorianCalendar(2023, 7, 28).getTime()); //Параметр тип:java.util.Date - Срок предъявлдения партии

            Parametrs.put("EtchingDate", new GregorianCalendar(2023, 7, 10).getTime()); //Параметр тип:java.util.Date - Дата травления

            Parametrs.put("Output", 90); //Параметр тип:Integer - Процент выхода
            
            Parametrs.put("Defects", 10); //Параметр тип:Integer - Процент брака

            Parametrs.put("Decree", new String("Тестовый доп. указ\nТестовый доп.указ")); //Параметр тип:String - Доп. указ

            JasperPrint Print = JasperFillManager.fillReport(Report, Parametrs, Collection.getCollection());

/////////////////////////////////////////////////////////////////EXPORT CODE//////////////////////////////////////////////////////////////////////////////////////////////////////////

            ReportExportManager ExportManager = new ReportExportManager(); // Объект менеджера экспорта таблиц в различные форматы файлов

            ExportManager.ExportToPdf("Отчет.pdf", Print); // Экспорт в PDF формат
            ExportManager.ExportToRtf("Отчет.rtf", Print); // Экспорт в RTF формат

            
            SimpleXlsReportConfiguration XlsConfig = new SimpleXlsReportConfiguration(); // Объект конфигурации XLS отчета
            XlsConfig.setWhitePageBackground(false); // Отключаем белый фон для клеток, оставляя их прозрачными
            XlsConfig.setOnePagePerSheet(true); // Для каждой новой таблицы создаем новую страницу в XLS документе
            ExportManager.ExportToXls("Отчет.xls", Print, new SimpleXlsExporterConfiguration(), XlsConfig); // Экспорт в XLS формат c передачей объектов для конфигурирования XLS документа
            ExportManager.ExportToXls("Отчет.ods", Print, new SimpleXlsExporterConfiguration(), XlsConfig);


            /*SimpleOdsReportConfiguration OdsConfig = new SimpleOdsReportConfiguration(); // Объект конфигурации ODS отчета
            OdsConfig.setFlexibleRowHeight(true);
            OdsConfig.setWhitePageBackground(false); // Отключаем белый фон для клеток, оставляя их прозрачными
            OdsConfig.setOnePagePerSheet(true); // Для каждой новой таблицы создаем новую страницу в ODS документе
            ExportManager.ExportToOds("Отчет.ods", Print, new SimpleOdsExporterConfiguration(), OdsConfig); // Экспорт в ODS формат(LibreOffice) c передачей объектов для конфигурирования ODS документа*/

            ExportManager.ExportToOdt("Отчет.odt", Print); // Экспорт в ODT формат
        }
        catch (SQLException e)
        {
            System.out.println("SQL Error " + e);
        }
        catch (JRException e)
        {
            System.out.println("JasperReport Error " + e);
        }

        System.out.println("Формирование отчетов завершено");
    }
}

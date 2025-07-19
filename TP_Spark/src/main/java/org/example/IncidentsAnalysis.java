package org.example;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.functions;

/**
 * @author achraf
 * @date 7/19/25
 */

public class IncidentsAnalysis {
    public static void main(String[] args) {
        SparkSession spark = SparkSession.builder()
                .appName("IncidentsAnalysis")
                .master("local[*]")
                .getOrCreate();

        Dataset<Row> df = spark.read()
                .option("header", "true")
                .option("inferSchema", "true")
                .csv("incidents.csv");

        // Question 1 : Nombre d'incidents par service
        System.out.println("Nombre d'incidents par service :");
        df.groupBy("service").count().show();

        // Question 2 : Deux années avec le plus d'incidents
        Dataset<Row> dfWithYear = df.withColumn("year", functions.year(df.col("date")));
        Dataset<Row> topYears = dfWithYear.groupBy("year").count()
                .orderBy(functions.col("count").desc())
                .limit(2);
        System.out.println("Top 2 années avec le plus d'incidents :");
        topYears.show();

        spark.stop();
    }
}


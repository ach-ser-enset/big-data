from pyspark.sql import SparkSession
from pyspark.sql.functions import year, sum as spark_sum, to_date

spark = SparkSession.builder.appName("TotalVentesParVilleEtAnnee").getOrCreate()

df = spark.read.option("delimiter", " ").option("header", False).csv("ventes.txt")
df = df.withColumnRenamed("_c0", "date") \
       .withColumnRenamed("_c1", "ville") \
       .withColumnRenamed("_c2", "produit") \
       .withColumnRenamed("_c3", "prix")

df = df.withColumn("prix", df["prix"].cast("float"))
df = df.withColumn("annee", year(to_date(df["date"], "yyyy-MM-dd")))

result = df.groupBy("ville", "annee").agg(spark_sum("prix").alias("total_ventes"))

result.show()

# spark.stop()  # Décommentez pour arrêter la session Spark

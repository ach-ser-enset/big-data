from pyspark.sql import SparkSession
from pyspark.sql.functions import sum as spark_sum

# Initialiser la session Spark
spark = SparkSession.builder.appName("TotalVentesParVille").getOrCreate()

# Lire le fichier ventes.txt
# Format attendu : date ville produit prix
# Exemple : 2024-01-01 Casablanca PC 1200

df = spark.read.option("delimiter", " ").option("header", False).csv("ventes.txt")
df = df.withColumnRenamed("_c0", "date") \
       .withColumnRenamed("_c1", "ville") \
       .withColumnRenamed("_c2", "produit") \
       .withColumnRenamed("_c3", "prix")

df = df.withColumn("prix", df["prix"].cast("float"))
result = df.groupBy("ville").agg(spark_sum("prix").alias("total_ventes"))

result.show()

# spark.stop()  # Décommentez pour arrêter la session Spark

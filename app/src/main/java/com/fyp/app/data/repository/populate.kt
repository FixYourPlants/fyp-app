package com.fyp.app.data.repository

import com.fyp.app.data.model.Care
import com.fyp.app.data.model.CareType
import com.fyp.app.data.model.Characteristic
import com.fyp.app.data.model.Difficulty
import com.fyp.app.data.model.Illness
import com.fyp.app.data.model.Opinion
import com.fyp.app.data.model.Plant
import com.fyp.app.data.model.User

val plants = mutableListOf<Plant>()
val illness = mutableListOf<Illness>()
val cares = mutableListOf<Care>()
val opinions = mutableListOf<Opinion>()
val users = mutableListOf<User>()
val characterictics = mutableListOf<Characteristic>()

/////////////////////// CUIDADOS /////////////////////////////
val cuidado1= Care(
    type= CareType.PLANT,
    actions= "Contratar a alguien"
)

/////////////////////// PLANTAS //////////////////////////////
val plant1 = Plant(
    name = "Manzana",
    scientificName = "Malus domestica",
    description = "Es una fruta de forma redondeada, con una piel suave y brillante que puede variar en color desde el verde al rojo, pasando por diferentes tonalidades intermedias. Es una fruta muy popular y versátil, utilizada tanto en postres como en platos salados, y es conocida por su sabor dulce y refrescante.",
    imageUrl = "URL de la imagen",
    difficulty = Difficulty.MEDIA,
    care = cuidado1,
    enfermedades = mutableListOf<Illness>(),
    characteristics = mutableListOf<Characteristic>()
)
val plant2 = Plant(
    name = "Pimiento Morrón",
    scientificName = "Capsicum annuum",
    description = "Se caracteriza por su forma gruesa y carnosa, con una piel brillante y un sabor suave y dulce. Los pimientos morrones suelen tener colores que van desde el verde al rojo, pasando por el amarillo y el naranja, dependiendo de su grado de madurez. Son muy apreciados en la cocina por su versatilidad y se utilizan en una amplia variedad de platos, tanto crudos como cocidos.",
    imageUrl = "URL de la imagen",
    difficulty = Difficulty.FACIL,
    care = cuidado1,
    enfermedades = mutableListOf<Illness>(),
    characteristics = mutableListOf<Characteristic>()
)
val plant3 = Plant(
    name = "Maíz",
    scientificName = "Zea mays",
    description = " El maíz es una planta cultivada ampliamente por sus mazorcas comestibles, que son una fuente importante de nutrientes como carbohidratos, proteínas y fibra. Es utilizado en una variedad de formas, desde alimentos básicos hasta ingredientes industriales.",
    imageUrl = "URL de la imagen",
    difficulty = Difficulty.FACIL,
    care = cuidado1,
    enfermedades = mutableListOf<Illness>(),
    characteristics = mutableListOf<Characteristic>()
)
val plant4 = Plant(
    name = "Arándanos",
    scientificName = "Vaccinium corymbosum",
    description = " Los arándanos son pequeñas bayas de color azul oscuro que crecen en arbustos de hoja perenne. Son conocidos por su sabor dulce y ligeramente ácido, así como por su alto contenido de antioxidantes y nutrientes beneficiosos para la salud.",
    imageUrl = "URL de la imagen",
    difficulty = Difficulty.FACIL,
    care = cuidado1,
    enfermedades = mutableListOf<Illness>(),
    characteristics = mutableListOf<Characteristic>()
)
val plant5 = Plant(
    name = "Fresa",
    scientificName = "Fragaria × ananassa",
    description = "La fresa es un fruto rojo brillante, pequeño y jugoso, que se encuentra en la planta del mismo nombre. Es conocida por su sabor dulce y fragante, así como por su textura suave y carnosa",
    imageUrl = "URL de la imagen",
    difficulty = Difficulty.FACIL,
    care = cuidado1,
    enfermedades = mutableListOf<Illness>(),
    characteristics = mutableListOf<Characteristic>()
)
val plant6 = Plant(
    name = "Cereza",
    scientificName = "Prunus cerasus",
    description = "Las cerezas son frutas pequeñas y redondas que crecen en árboles caducifolios del género Prunus. Existen dos tipos principales de cerezas: las dulces y las ácidas. Las cerezas dulces son conocidas por su sabor suave y dulce, mientras que las cerezas ácidas tienen un sabor más ácido y se utilizan comúnmente en la preparación de postres y conservas.",
    imageUrl = "URL de la imagen",
    difficulty = Difficulty.FACIL,
    care = cuidado1,
    enfermedades = mutableListOf<Illness>(),
    characteristics = mutableListOf<Characteristic>()
)
val plant7 = Plant(
    name = "Melocotón",
    scientificName = "Prunus persica",
    description = " El melocotón es una fruta de pulpa suave y jugosa que crece en el árbol del mismo nombre. Conocido por su piel aterciopelada y su sabor dulce y aromático, el melocotón es una fruta popular en muchas partes del mundo. ",
    imageUrl = "URL de la imagen",
    difficulty = Difficulty.FACIL,
    care = cuidado1,
    enfermedades = mutableListOf<Illness>(),
    characteristics = mutableListOf<Characteristic>()
)
val plant8 = Plant(
    name = "Patata",
    scientificName = "Solanum tuberosum",
    description = " La patata es un tubérculo comestible que pertenece a la familia de las solanáceas. Es una de las principales fuentes de carbohidratos en la dieta humana y se consume en todo el mundo en una amplia variedad de platos.",
    imageUrl = "URL de la imagen",
    difficulty = Difficulty.FACIL,
    care = cuidado1,
    enfermedades = mutableListOf<Illness>(),
    characteristics = mutableListOf<Characteristic>()
)
val plant9 = Plant(
    name = "Haba de Soja",
    scientificName = "Glycine max",
    description = "La haba de soja, también conocida como soja o frijol de soja, es una leguminosa originaria de Asia oriental que se cultiva ampliamente por sus semillas comestibles ricas en proteínas y aceites vegetales.",
    imageUrl = "URL de la imagen",
    difficulty = Difficulty.ALTA,
    care = cuidado1,
    enfermedades = mutableListOf<Illness>(),
    characteristics = mutableListOf<Characteristic>()
)
val plant10 = Plant(
    name = "Frambuesa",
    scientificName = "Rubus idaeus",
    description = "La frambuesa es una pequeña fruta roja que crece en arbustos del género Rubus. Conocida por su sabor dulce y ligeramente ácido, la frambuesa es una fruta muy apreciada en la cocina por su versatilidad y su capacidad para realzar una amplia variedad de platos.",
    imageUrl = "URL de la imagen",
    difficulty = Difficulty.FACIL,
    care = cuidado1,
    enfermedades = mutableListOf<Illness>(),
    characteristics = mutableListOf<Characteristic>()
)
val plant11 = Plant(
    name = "Tomate",
    scientificName = "Solanum lycopersicum",
    description = "El tomate es una fruta de pulpa jugosa y piel fina, de color rojo brillante en su madurez, que crece en la planta del mismo nombre. Aunque es comúnmente utilizado como una verdura en la cocina, botánicamente es una fruta. Los tomates son una fuente importante de nutrientes como la vitamina C, el potasio y el licopeno, un antioxidante que se ha asociado con varios beneficios para la salud. ",
    imageUrl = "URL de la imagen",
    difficulty = Difficulty.FACIL,
    care = cuidado1,
    enfermedades = mutableListOf<Illness>(),
    characteristics = mutableListOf<Characteristic>()
)
val plant12 = Plant(
    name = "Uva",
    scientificName = "Vitis vinifera",
    description = " Las uvas son frutas pequeñas y redondas que crecen en racimos en las vides del género Vitis. Son conocidas por su sabor dulce y jugoso, así como por su versatilidad en la cocina y en la producción de vino. Las uvas pueden ser de diferentes colores, incluyendo verde, rojo, morado y negro, dependiendo de la variedad.",
    imageUrl = "URL de la imagen",
    difficulty = Difficulty.MEDIA,
    care = cuidado1,
    enfermedades = mutableListOf<Illness>(),
    characteristics = mutableListOf<Characteristic>()
)

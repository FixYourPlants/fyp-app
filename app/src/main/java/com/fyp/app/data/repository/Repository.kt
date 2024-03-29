package com.fyp.app.data.repository

import com.fyp.app.data.model.Care
import com.fyp.app.data.model.CareType
import com.fyp.app.data.model.Characteristic
import com.fyp.app.data.model.Difficulty
import com.fyp.app.data.model.Illness
import com.fyp.app.data.model.Opinion
import com.fyp.app.data.model.Plant
import com.fyp.app.data.model.PlantIllness
import com.fyp.app.data.model.User

object Repository {
    val plants:MutableList<Plant> = mutableListOf()
    val illness:MutableList<Illness> = mutableListOf()
    val opinions:MutableList<Opinion> = mutableListOf()
    val users:MutableList<User> = mutableListOf()
    val characteristics:MutableList<Characteristic> = mutableListOf()
    val plantIllness:MutableList<PlantIllness> = mutableListOf()
    fun populate() {
    /////////////////////// CUIDADOS /////////////////////////////
        val cuidadoManzana = Care(type = CareType.PLANT, actions = "Podar regularmente para mantener la forma y estimular el crecimiento. Proporcionar riego regular y protección contra plagas y enfermedades.")
        val cuidadoPimientoMorron = Care(type = CareType.PLANT, actions = "Proporcionar riego regular y asegurarse de que reciban suficiente luz solar. Fertilizar periódicamente y proteger contra plagas como pulgones.")
        val cuidadoMaiz = Care(type = CareType.PLANT, actions = "Plantar en suelo bien drenado y proporcionar riego regular. Controlar malezas y fertilizar según sea necesario.")
        val cuidadoArandanos = Care(type = CareType.PLANT, actions = "Plantar en suelo ácido y bien drenado. Proporcionar riego regular y protección contra pájaros y enfermedades fúngicas.")
        val cuidadoFresa = Care(type = CareType.PLANT, actions = "Plantar en suelo rico en materia orgánica y proporcionar riego regular. Retirar corredores para fomentar la producción de frutos.")
        val cuidadoCereza = Care(type = CareType.PLANT, actions = "Proporcionar riego regular y protección contra enfermedades fúngicas y plagas como los pájaros.")
        val cuidadoMelocoton = Care(type = CareType.PLANT, actions = "Proporcionar riego regular y protección contra plagas y enfermedades. Podar en invierno para mantener la forma y estimular la fructificación.")
        val cuidadoPatata = Care(type = CareType.PLANT, actions = "Plantar en suelo bien drenado y proporcionar riego regular. Controlar plagas como el escarabajo de la patata.")
        val cuidadoHabaDeSoja = Care(type = CareType.PLANT, actions = "Plantar en suelo bien drenado y proporcionar riego regular. Controlar malezas y proteger contra enfermedades fúngicas.")
        val cuidadoFrambuesa = Care(type = CareType.PLANT, actions = "Podar las cañas viejas después de la fructificación y proporcionar riego regular. Proteger contra enfermedades como el moho.")
        val cuidadoTomate = Care(type = CareType.PLANT, actions = "Proporcionar riego regular y fertilizar según sea necesario. Podar y entutorar para promover un crecimiento saludable.")
        val cuidadoUva = Care(type = CareType.PLANT, actions = "Proporcionar riego regular y protección contra plagas como la polilla de la uva. Podar en invierno para promover una buena producción.")

        val cuidadoSarna = Care(type = CareType.ILLNESS, actions = "1. Aplicar fungicidas específicos para la sarna siguiendo las instrucciones del fabricante.\n2. Eliminar y destruir las hojas infectadas para prevenir la propagación.")
        val cuidadoOxido = Care(type = CareType.ILLNESS, actions = "1. Retirar y eliminar las partes de la planta afectadas.\n2. Aplicar fungicidas preventivos al inicio de la temporada de crecimiento.")
        val cuidadoOidio = Care(type = CareType.ILLNESS, actions = "1. Mantener una buena circulación de aire alrededor de las plantas para reducir la humedad.\n2. Aplicar tratamientos fungicidas a intervalos regulares.")
        val cuidadoTizon = Care(type = CareType.ILLNESS, actions = "1. Rotar cultivos para evitar la propagación del tizón.\n2. Mantener el suelo limpio de restos vegetales para reducir la propagación de esporas.")
        val cuidadoManchaBacteriana = Care(type = CareType.ILLNESS, actions = "1. Utilizar prácticas de cultivo que reduzcan la humedad en las hojas.\n2. Aplicar tratamientos bactericidas según las recomendaciones del fabricante.")
        val cuidadoManchaFoliar = Care(type = CareType.ILLNESS, actions = "1. Evitar el riego por aspersión para reducir la humedad en las hojas.\n2. Eliminar y destruir las hojas afectadas para prevenir la propagación.")
        val cuidadoVirusMosaico = Care(type = CareType.ILLNESS, actions = "1. Eliminar y destruir las plantas infectadas para prevenir la propagación del virus.\n2. Controlar los insectos vectores que puedan transmitir el virus.")
        val cuidadoVirusRizadoAmarillo = Care(type = CareType.ILLNESS, actions = "1. Utilizar variedades resistentes cuando sea posible.\n2. Controlar las malas hierbas que puedan servir como hospederos alternativos.")
        val cuidadoMoho = Care(type = CareType.ILLNESS, actions = "1. Mantener un buen drenaje del suelo para reducir la humedad.\n2. Aplicar fungicidas preventivos en períodos de alta humedad.")
        val cuidadoPodredumbreNegra = Care(type = CareType.ILLNESS, actions = "1. Evitar el exceso de riego para reducir la humedad en el suelo.\n2. Eliminar y destruir los frutos afectados para prevenir la propagación.")

    /////////////////////// PLANTAS //////////////////////////////
        val plant1 = Plant(name = "Manzana", scientificName = "Malus domestica", description = "Es una fruta de forma redondeada, con una piel suave y brillante que puede variar en color desde el verde al rojo, pasando por diferentes tonalidades intermedias. Es una fruta muy popular y versátil, utilizada tanto en postres como en platos salados, y es conocida por su sabor dulce y refrescante.", imageUrl = "URL de la imagen", difficulty = Difficulty.MEDIA, care = cuidadoManzana)
        val plant2 = Plant(name = "Pimiento Morrón", scientificName = "Capsicum annuum", description = "Se caracteriza por su forma gruesa y carnosa, con una piel brillante y un sabor suave y dulce. Los pimientos morrones suelen tener colores que van desde el verde al rojo, pasando por el amarillo y el naranja, dependiendo de su grado de madurez. Son muy apreciados en la cocina por su versatilidad y se utilizan en una amplia variedad de platos, tanto crudos como cocidos.", imageUrl = "URL de la imagen", difficulty = Difficulty.FACIL, care = cuidadoPimientoMorron)
        val plant3 = Plant(name = "Maíz", scientificName = "Zea mays", description = " El maíz es una planta cultivada ampliamente por sus mazorcas comestibles, que son una fuente importante de nutrientes como carbohidratos, proteínas y fibra. Es utilizado en una variedad de formas, desde alimentos básicos hasta ingredientes industriales.", imageUrl = "URL de la imagen", difficulty = Difficulty.FACIL, care = cuidadoMaiz)
        val plant4 = Plant(name = "Arándanos", scientificName = "Vaccinium corymbosum", description = " Los arándanos son pequeñas bayas de color azul oscuro que crecen en arbustos de hoja perenne. Son conocidos por su sabor dulce y ligeramente ácido, así como por su alto contenido de antioxidantes y nutrientes beneficiosos para la salud.", imageUrl = "URL de la imagen", difficulty = Difficulty.FACIL, care = cuidadoArandanos)
        val plant5 = Plant(name = "Fresa", scientificName = "Fragaria × ananassa", description = "La fresa es un fruto rojo brillante, pequeño y jugoso, que se encuentra en la planta del mismo nombre. Es conocida por su sabor dulce y fragante, así como por su textura suave y carnosa", imageUrl = "URL de la imagen", difficulty = Difficulty.FACIL, care = cuidadoFresa)
        val plant6 = Plant(name = "Cereza", scientificName = "Prunus cerasus", description = "Las cerezas son frutas pequeñas y redondas que crecen en árboles caducifolios del género Prunus. Existen dos tipos principales de cerezas: las dulces y las ácidas. Las cerezas dulces son conocidas por su sabor suave y dulce, mientras que las cerezas ácidas tienen un sabor más ácido y se utilizan comúnmente en la preparación de postres y conservas.", imageUrl = "URL de la imagen", difficulty = Difficulty.FACIL, care = cuidadoCereza)
        val plant7 = Plant(name = "Melocotón", scientificName = "Prunus persica", description = " El melocotón es una fruta de pulpa suave y jugosa que crece en el árbol del mismo nombre. Conocido por su piel aterciopelada y su sabor dulce y aromático, el melocotón es una fruta popular en muchas partes del mundo. ", imageUrl = "URL de la imagen", difficulty = Difficulty.FACIL, care = cuidadoMelocoton)
        val plant8 = Plant(name = "Patata", scientificName = "Solanum tuberosum", description = " La patata es un tubérculo comestible que pertenece a la familia de las solanáceas. Es una de las principales fuentes de carbohidratos en la dieta humana y se consume en todo el mundo en una amplia variedad de platos.", imageUrl = "URL de la imagen", difficulty = Difficulty.FACIL, care = cuidadoPatata)
        val plant9 = Plant(name = "Haba de Soja", scientificName = "Glycine max", description = "La haba de soja, también conocida como soja o frijol de soja, es una leguminosa originaria de Asia oriental que se cultiva ampliamente por sus semillas comestibles ricas en proteínas y aceites vegetales.", imageUrl = "URL de la imagen", difficulty = Difficulty.ALTA, care = cuidadoHabaDeSoja)
        val plant10 = Plant(name = "Frambuesa", scientificName = "Rubus idaeus", description = "La frambuesa es una pequeña fruta roja que crece en arbustos del género Rubus. Conocida por su sabor dulce y ligeramente ácido, la frambuesa es una fruta muy apreciada en la cocina por su versatilidad y su capacidad para realzar una amplia variedad de platos.", imageUrl = "URL de la imagen", difficulty = Difficulty.FACIL, care = cuidadoFrambuesa)
        val plant11 = Plant(name = "Tomate", scientificName = "Solanum lycopersicum", description = "El tomate es una fruta de pulpa jugosa y piel fina, de color rojo brillante en su madurez, que crece en la planta del mismo nombre. Aunque es comúnmente utilizado como una verdura en la cocina, botánicamente es una fruta. Los tomates son una fuente importante de nutrientes como la vitamina C, el potasio y el licopeno, un antioxidante que se ha asociado con varios beneficios para la salud. ", imageUrl = "URL de la imagen", difficulty = Difficulty.FACIL, care = cuidadoTomate)
        val plant12 = Plant(name = "Uva", scientificName = "Vitis vinifera", description = " Las uvas son frutas pequeñas y redondas que crecen en racimos en las vides del género Vitis. Son conocidas por su sabor dulce y jugoso, así como por su versatilidad en la cocina y en la producción de vino. Las uvas pueden ser de diferentes colores, incluyendo verde, rojo, morado y negro, dependiendo de la variedad.", imageUrl = "URL de la imagen", difficulty = Difficulty.MEDIA, care = cuidadoUva)
        val plant13 = Plant(name = "Calabacín", scientificName = "Cucurbita pepo", description = " Las uvas son frutas pequeñas y redondas que crecen en racimos en las vides del género Vitis. Son conocidas por su sabor dulce y jugoso, así como por su versatilidad en la cocina y en la producción de vino. Las uvas pueden ser de diferentes colores, incluyendo verde, rojo, morado y negro, dependiendo de la variedad.", imageUrl = "URL de la imagen", difficulty = Difficulty.MEDIA, care = cuidadoUva)

        plants.add(plant1)
        plants.add(plant2)
        plants.add(plant3)
        plants.add(plant4)
        plants.add(plant5)
        plants.add(plant6)
        plants.add(plant7)
        plants.add(plant8)
        plants.add(plant9)
        plants.add(plant10)
        plants.add(plant11)
        plants.add(plant12)
        plants.add(plant13)

    /////////////////////// ILLNESS //////////////////////////////
        val sarna = Illness(name = "Sarna", description = "La sarna es una enfermedad fúngica común en árboles frutales como manzanos y perales. Se caracteriza por la formación de manchas en las hojas y frutos, lo que puede afectar su crecimiento y calidad.", imageUrl = "", care = cuidadoSarna)
        val oxido = Illness(name = "Óxido", description = "El óxido es una enfermedad fúngica que afecta a diversas plantas, incluyendo cereales, rosas y árboles frutales. Se manifiesta como manchas de color naranja o marrón en las hojas y puede debilitar la planta si no se controla.", imageUrl = "", care = cuidadoOxido)
        val oidio = Illness(name = "Oídio", description = "El oídio es una enfermedad causada por hongos que afecta a una amplia variedad de plantas, incluyendo rosas, calabazas y vid. Se caracteriza por el crecimiento de un polvo blanco o grisáceo en las hojas, tallos y flores.", imageUrl = "", care = cuidadoOidio)
        val tizon = Illness(name = "Tizón", description = "El tizón es una enfermedad fúngica que afecta a muchas plantas cultivadas, incluyendo maíz, patatas y tomates. Se manifiesta como manchas oscuras en las hojas y puede causar daños significativos si no se trata adecuadamente.", imageUrl = "", care = cuidadoTizon)
        val manchaBacteriana = Illness(name = "Mancha bacteriana", description = "La mancha bacteriana es una enfermedad causada por bacterias que afecta a plantas como tomates, pimientos y pepinos. Se caracteriza por la formación de manchas húmedas y oscuras en las hojas y frutos, lo que puede llevar a la pudrición de estos tejidos.", imageUrl = "", care = cuidadoManchaBacteriana)
        val manchaFoliar = Illness(name = "Mancha foliar", description = "La mancha foliar es una enfermedad fúngica que afecta a una amplia gama de plantas, desde árboles frutales hasta cultivos de hortalizas. Se presenta como manchas circulares o irregulares en las hojas, que pueden expandirse y fusionarse si las condiciones son favorables.", imageUrl = "", care = cuidadoManchaFoliar)
        val virusMosaico = Illness(name = "Virus del mosaico", description = "El virus del mosaico es una enfermedad viral que afecta a muchas plantas, incluyendo tomates, pepinos y calabacines. Se manifiesta como un moteado característico en las hojas, que puede reducir la capacidad de la planta para realizar la fotosíntesis y afectar su rendimiento.", imageUrl = "", care = cuidadoVirusMosaico)
        val virusRizadoAmarillo = Illness(name = "Virus rizado amarillo", description = "El virus rizado amarillo es una enfermedad viral que afecta a diversas plantas cultivadas, incluyendo tomates, patatas y remolachas. Se caracteriza por el amarillamiento y deformación de las hojas, así como la reducción en el rendimiento de la planta.", imageUrl = "", care = cuidadoVirusRizadoAmarillo)
        val moho = Illness(name = "Moho", description = "El moho es una enfermedad fúngica que afecta a muchas plantas, incluyendo hortalizas, árboles frutales y plantas ornamentales. Se manifiesta como un crecimiento micelial blanco, gris o negro en las hojas, tallos o frutos, lo que puede causar la pudrición de los tejidos.", imageUrl = "", care = cuidadoMoho)
        val podredumbreNegra = Illness(name = "Podredumbre negra", description = "La podredumbre negra es una enfermedad causada por hongos que afecta a diversas plantas, incluyendo vides, tomates y fresas. Se caracteriza por la descomposición de los tejidos, que adquieren un color oscuro o negro, y puede propagarse rápidamente en condiciones húmedas.", imageUrl = "", care = cuidadoPodredumbreNegra)

        illness.add(sarna)
        illness.add(oxido)
        illness.add(oidio)
        illness.add(tizon)
        illness.add(manchaBacteriana)
        illness.add(manchaFoliar)
        illness.add(virusMosaico)
        illness.add(virusRizadoAmarillo)
        illness.add(moho)
        illness.add(podredumbreNegra)

    /////////////////////// USERS //////////////////////////////
        val user1 = User(name = "User 1", email = "user1@user.com", password = "12345USER1!" , imageUrl = "PLACEHOLDER")
        val user2 = User(name = "User 2", email = "user2@user.com", password = "12345USER2!", imageUrl = "PLACEHOLDER")
        val user3 = User(name = "User 3", email = "user3@user.com", password = "12345USER3!", imageUrl = "PLACEHOLDER")
        val user4 = User(name = "User 4", email = "user4@user.com", password = "12345USER4!", imageUrl = "PLACEHOLDER")
        val user5 = User(name = "User 5", email = "user5@user.com", password = "12345USER5!", imageUrl = "PLACEHOLDER")

        users.add(user1)
        users.add(user2)
        users.add(user3)
        users.add(user4)
        users.add(user5)

    /////////////////////// OPINIONS //////////////////////////////
        val opinion1 = Opinion(user = user1, title = "Opinion 1", plant = plant1, description = "Texto de la Opinion 1", email = "user1@user.com")
        val opinion2 = Opinion(user = user2, title = "Opinion 2", plant = plant1, description = "Texto de la Opinion 2", email = "user2@user.com")
        val opinion3 = Opinion(user = user3, title = "Opinion 3", plant = plant1, description = "Texto de la Opinion 3", email = "user3@user.com")
        val opinion4 = Opinion(user = user4, title = "Opinion 4", plant = plant1, description = "Texto de la Opinion 4", email = "user4@user.com")
        val opinion5 = Opinion(user = user5, title = "Opinion 5", plant = plant1, description = "Texto de la Opinion 5", email = "user5@user.com")
        val opinion6 = Opinion(user = user1, title = "Las patatas estan sobrevaloradas", plant = plant8, description = "No voy a elaborar más", email = "user1@user.com")

        opinions.add(opinion1)
        opinions.add(opinion2)
        opinions.add(opinion3)
        opinions.add(opinion4)
        opinions.add(opinion5)
        opinions.add(opinion6)

    /////////////////////// CHARACTERISTICS //////////////////////////////
        val characteristic1= Characteristic(name = "Dulce", plant= listOf(plant4,plant5))
        val characteristic2= Characteristic(name = "Arbusto", plant=listOf(plant4,plant5,plant6,plant10,plant12))
        val characteristic3= Characteristic(name = "Árbol", plant=listOf(plant1,plant6,plant7))
        val characteristic4= Characteristic(name = "Oriental", plant=listOf(plant9))
        val characteristic5= Characteristic(name = "Tubérculo", plant=listOf(plant2,plant2,plant5,plant6,plant11))
        val characteristic6= Characteristic(name = "Rojo", plant=listOf(plant4,plant5,plant6,plant10,plant12))
        val characteristic7= Characteristic(name = "Mediterráneo", plant=listOf(plant2,plant5,plant8,plant11))
        val characteristic8= Characteristic(name = "Mejor Zumo", plant=listOf(plant7))

        characteristics.add(characteristic1)
        characteristics.add(characteristic2)
        characteristics.add(characteristic3)
        characteristics.add(characteristic4)
        characteristics.add(characteristic5)
        characteristics.add(characteristic6)
        characteristics.add(characteristic7)
        characteristics.add(characteristic8)

    /////////////////////// OPINIONS //////////////////////////////
        val plantIllness1= PlantIllness(plant = plant1, illness = sarna)
        val plantIllness2= PlantIllness(plant = plant1, illness = oxido)
        val plantIllness3= PlantIllness(plant = plant2, illness = oidio)
        val plantIllness4= PlantIllness(plant = plant3, illness = oidio)
        val plantIllness5= PlantIllness(plant = plant3, illness = oxido)
        val plantIllness6= PlantIllness(plant = plant3, illness = tizon)
        val plantIllness7= PlantIllness(plant = plant8, illness = tizon)
        val plantIllness8= PlantIllness(plant = plant11, illness = virusMosaico)
        val plantIllness9= PlantIllness(plant = plant11, illness = virusRizadoAmarillo)
        val plantIllness10= PlantIllness(plant = plant11, illness = manchaBacteriana)
        val plantIllness11= PlantIllness(plant = plant11, illness = manchaFoliar)
        val plantIllness12= PlantIllness(plant = plant11, illness = moho)
        val plantIllness13= PlantIllness(plant = plant11, illness = tizon)
        val plantIllness14= PlantIllness(plant = plant12, illness = podredumbreNegra)

        plantIllness.add(plantIllness1)
        plantIllness.add(plantIllness2)
        plantIllness.add(plantIllness3)
        plantIllness.add(plantIllness1)
        plantIllness.add(plantIllness4)
        plantIllness.add(plantIllness1)
        plantIllness.add(plantIllness5)
        plantIllness.add(plantIllness6)
        plantIllness.add(plantIllness7)
        plantIllness.add(plantIllness8)
        plantIllness.add(plantIllness9)
        plantIllness.add(plantIllness10)
        plantIllness.add(plantIllness11)
        plantIllness.add(plantIllness12)
        plantIllness.add(plantIllness13)
        plantIllness.add(plantIllness14)
    }

}
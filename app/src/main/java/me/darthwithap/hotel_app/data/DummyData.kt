package me.darthwithap.hotel_app.data

import me.darthwithap.hotel_app.domain.models.Amenity
import me.darthwithap.hotel_app.domain.models.Hotel
import me.darthwithap.hotel_app.domain.models.HotelDetails
import me.darthwithap.hotel_app.domain.models.Location
import me.darthwithap.hotel_app.domain.models.Review
import me.darthwithap.hotel_app.domain.models.Room
import me.darthwithap.hotel_app.domain.models.RoomType
import me.darthwithap.hotel_app.domain.models.TopSpot
import me.darthwithap.hotel_app.domain.models.User
import me.darthwithap.hotel_app.domain.values.AmenityIconResId
import me.darthwithap.hotel_app.domain.values.Gender
import me.darthwithap.hotel_app.ui.utils.toDateString
import java.time.LocalDate
import java.time.Period
import java.util.UUID
import kotlin.math.pow
import kotlin.math.roundToInt
import kotlin.random.Random

fun randomHotels(count: Int): List<HotelDetails> {
  val hotels = mutableListOf<HotelDetails>()
  repeat(count) {
    val address = hotelAddresses.random()
    val images = randomHotelPhotoUrls()
    val reviews = randomReviews()
    val hotel = Hotel(
      id = UUID.randomUUID().toString(),
      thumbnailImage = images.first(),
      name = hotelNames.random(),
      officialRating = if (reviews.isNotEmpty()) ((reviews.sumOf { it.rating } / reviews.size) + Random.nextDouble(
        0.0,
        0.99
      )).roundTo(2) else 0.0,
      address = address.second,
      location = address.first,
      numberOfReviews = Random.nextInt(10, 1001),
      primaryContact = randomPrimaryContact(address.second.city),
    )
    val rooms = randomRooms()
    hotels.add(
      HotelDetails(
        hotel = hotel,
        description = hotelDescriptions.random(),
        images = images,
        hotelInformation = randomHotelInformation(),
        features = randomHotelFeatures(),
        reviews = reviews,
        rooms = rooms,
        roomTypes = rooms.distinctBy { it.roomType }.map { it.roomType },
        topSpots = randomTopSpots(),
        bookedDates = randomBookedDates()
      )
    )
  }
  return hotels
}

private val hotelNames = listOf(
  "Luxury Haven Hotel",
  "Riverside Retreat",
  "Whispering Pines Lodge",
  "Moonlit Mirage Hotel",
  "Velvet Horizon Suites",
  "Radiant Meadows Inn",
  "Twilight Grove Manor",
  "Starlight Haven Hotel",
  "Tranquil Harbor Retreat",
  "Aurora Skies Inn",
  "Secret Garden Manor",
  "Dreamscape Suites",
  "Emberwood Lodge",
  "Midnight Velvet Inn"
)

private val hotelDescriptions = listOf(
  "Experience unparalleled luxury and comfort at the Luxury Haven Hotel. Located in the heart of London, this hotel offers breathtaking views of the city skyline and impeccable service.",
  "Enjoy a peaceful escape at the Riverside Retreat. Nestled along the Thames River, this hotel offers a serene atmosphere and easy access to London's attractions",
  "Experience modern sophistication at the Urban Elegance Suites. Located in the bustling city center, this hotel offers chic accommodations and easy access to shopping and entertainment.",
  "Escape to a coastal haven where the rhythm of the waves soothes your soul. Luxurious rooms and suites offer stunning ocean views, while the sound of seagulls creates a tranquil ambiance. Explore pristine beaches, indulge in fresh seafood, and let the sea breeze carry your worries away.",
  "Immerse yourself in the heartbeat of the city from the comfort of our modern urban oasis. Stylish rooms provide a sanctuary amid the urban hustle, and panoramic cityscapes light up your nights. Savor diverse culinary offerings, discover cultural landmarks, and experience the vibrant energy of city life.",
  "Retreat to a mountain lodge where nature's majesty surrounds you. Cozy cabins and chalets offer warmth and comfort, while towering peaks create a breathtaking backdrop. Explore hiking trails, breathe in the crisp mountain air, and find solace in the embrace of the great outdoors.",
  "Step back in time at our historic establishment where stories of the past come alive. Elegant rooms are adorned with period furnishings, and the architecture reflects a bygone era. Wander through charming cobblestone streets, discover local traditions, and relish the timeless beauty of history.",
  "Find peace in the tranquil embrace of the countryside. Rustic lodges and cottages provide a cozy retreat, surrounded by rolling fields and picturesque landscapes. Immerse yourself in outdoor activities, enjoy farm-to-table cuisine, and let the unhurried pace rejuvenate your spirit.",
  "Experience contemporary comfort in the heart of modern convenience. Chic rooms offer sleek design and state-of-the-art amenities, while nearby attractions provide endless entertainment. Unwind by the pool, indulge in gourmet dining, and revel in the ease of a seamless urban escape.",
  "Discover an enchanting haven nestled within lush gardens. Quaint rooms and suites offer garden views, where vibrant blooms and serene pathways invite exploration. Immerse yourself in the beauty of nature, enjoy al fresco dining, and let the harmony of the gardens restore your balance.",
  "Seek adventure in the heart of the wilderness, where rugged charm meets untamed beauty. Cabins and lodges provide a rustic retreat, surrounded by towering trees and untrodden trails. Embark on outdoor expeditions, gather around campfires, and reconnect with the primal allure of the wild.",
  "Find seclusion in a timeless hideaway where tranquility reigns supreme. Intimate cottages and lodgings offer privacy and comfort, creating the perfect escape from the demands of daily life. Immerse yourself in meditative practices, embrace holistic wellness, and rediscover your inner harmony.",
  "Immerse yourself in the local culture from the heart of our vibrant establishment. Rooms reflect the region's traditions, while nearby markets, galleries, and performances offer a glimpse into the community's soul. Engage with local artisans, savor authentic cuisine, and let the cultural tapestry unfold around you.",
  "Indulge in timeless grandeur at the Royal Manor Hotel. Nestled in the charming countryside just outside of London, this opulent estate is surrounded by lush gardens and scenic landscapes. Guests can enjoy elegant, spacious rooms with antique furnishings, savor gourmet dining experiences, and explore historic grounds."
)

private val topSpots = listOf(
  TopSpot(
    title = "Ron Diving",
    location = Location(0.070133, 0.076143),
    placeName = "Nusa Dua",
    pricePerPerson = 19.0,
    thumbnailImage = "https://images.unsplash.com/photo-1682687982502-1529b3b33f85?q=80&w=2370&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
    isWishlisted = Random.nextBoolean()
  ),
  TopSpot(
    title = "London Eye Visit",
    location = Location(0.076132, 51.508530),
    placeName = "London",
    pricePerPerson = 30.0,
    thumbnailImage = "https://images.unsplash.com/photo-1526487239775-b6e778742d48?q=80&w=5064&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
    isWishlisted = Random.nextBoolean()
  ),
  TopSpot(
    title = "Tower Bridge Tour",
    location = Location(0.076133, 51.508530),
    placeName = "London",
    pricePerPerson = 25.0,
    thumbnailImage = "https://images.unsplash.com/photo-1533929736458-ca588d08c8be?q=80&w=5070&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
    isWishlisted = Random.nextBoolean()
  ),
  TopSpot(
    title = "Buckingham Palace Gate",
    location = Location(0.076132, 51.508530),
    placeName = "London",
    pricePerPerson = 22.0,
    thumbnailImage = "https://images.unsplash.com/photo-1662712272177-99b30ab524ac?q=80&w=5070&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
    isWishlisted = Random.nextBoolean()
  ),
  TopSpot(
    title = "Historic Royal Walk",
    location = Location(0.076132, 51.508530),
    placeName = "London",
    pricePerPerson = 18.0,
    thumbnailImage = "https://images.unsplash.com/photo-1532264251691-2ad92a2ec88f?q=80&w=3260&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
    isWishlisted = Random.nextBoolean()
  ),
  TopSpot(
    title = "Manchester Museum Tour",
    location = Location(2.244644, 53.483959),
    placeName = "Manchester",
    pricePerPerson = 15.0,
    thumbnailImage = "https://images.unsplash.com/photo-1668878189210-d29eb877a6ff?q=80&w=2322&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
    isWishlisted = Random.nextBoolean()
  ),
  TopSpot(
    title = "Old Trafford Stadium",
    location = Location(2.244644, 53.483959),
    placeName = "Manchester",
    pricePerPerson = 35.0,
    thumbnailImage = "https://images.unsplash.com/photo-1610201417828-29dd1173d62f?q=80&w=4032&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
    isWishlisted = Random.nextBoolean()
  ),
  TopSpot(
    title = "Birmingham Science Museum",
    location = Location(1.898575, 52.489471),
    placeName = "Birmingham",
    pricePerPerson = 12.0,
    thumbnailImage = "https://images.unsplash.com/photo-1534235826754-0a3572d1d6d5?q=80&w=4323&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
    isWishlisted = Random.nextBoolean()
  ),
  TopSpot(
    title = "Canal Boat Ride",
    location = Location(1.898575, 52.489471),
    placeName = "Birmingham",
    pricePerPerson = 20.0,
    thumbnailImage = "https://images.unsplash.com/photo-1583354009936-42ef436d5f24?q=80&w=1000&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8M3x8Ym9hdCUyMHRvdXJ8ZW58MHx8MHx8fDA%3D",
    isWishlisted = Random.nextBoolean()
  ),
  TopSpot(
    title = "Gaudi Architecture Tour",
    location = Location(2.154007, 41.390205),
    placeName = "Barcelona",
    pricePerPerson = 25.0,
    thumbnailImage = "https://images.unsplash.com/photo-1657705737771-6de18c9123c8?w=800&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MXx8R2F1ZGklMjBBcmNoaXRlY3R1cmUlMjBUb3VyfGVufDB8fDB8fHww",
    isWishlisted = Random.nextBoolean()
  ),
  TopSpot(
    title = "Royal Palace of Madrid",
    location = Location(2.154007, 41.390205),
    placeName = "Madrid",
    pricePerPerson = 28.0,
    thumbnailImage = "https://plus.unsplash.com/premium_photo-1697730316098-392ac14b93fe?w=800&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MXx8Um95YWwlMjBQYWxhY2UlMjBvZiUyME1hZHJpZHxlbnwwfHwwfHx8MA%3D%3D",
    isWishlisted = Random.nextBoolean()
  ),
  TopSpot(
    title = "Santiago Bernabéu Stadium",
    location = Location(2.154007, 41.390205),
    placeName = "Madrid",
    pricePerPerson = 28.0,
    thumbnailImage = "https://images.unsplash.com/photo-1522778034537-20a2486be803?w=800&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Nnx8TWFkcmlkJTIwU3RhZGl1bXxlbnwwfHwwfHx8MA%3D%3D",
    isWishlisted = Random.nextBoolean()
  )
)

private val hotelAddresses = listOf(
  Location(
    longitude = 0.076132,
    latitude = 51.508530
  ) to Hotel.Address(
    country = "England",
    city = "London",
    addressLine1 = "122 Park Lane",
    addressLine2 = "122 Park Lane"
  ),
  Location(
    longitude = 0.076133,
    latitude = 0.076133
  ) to Hotel.Address(
    country = "England",
    city = "London",
    addressLine1 = "123 Garden Lane",
    addressLine2 = "123 Garden Lane"
  ),
  Location(
    longitude = 0.076132,
    latitude = 51.508530
  ) to Hotel.Address(
    country = "England",
    city = "London",
    addressLine1 = "78 High Street",
    addressLine2 = "78 High Street"
  ),
  Location(
    longitude = 2.244644,
    latitude = 53.483959
  ) to Hotel.Address(
    country = "England",
    city = "Manchester",
    addressLine1 = "17 Willow Crescent",
    addressLine2 = "17 Willow Crescent"
  ),
  Location(
    longitude = 1.898575,
    latitude = 52.489471
  ) to Hotel.Address(
    country = "England",
    city = "Birmingham",
    addressLine1 = "8 Ivy Lane",
    addressLine2 = "8 Ivy Lane"
  ),
  Location(
    longitude = 2.154007,
    latitude = 41.390205
  ) to Hotel.Address(
    country = "Spain",
    city = "Barcelona",
    addressLine1 = "Calle del Sol, 23",
    addressLine2 = "Calle del Sol, 23"
  ),
  Location(
    longitude = 2.154007,
    latitude = 41.390205
  ) to Hotel.Address(
    country = "Spain",
    city = "Madrid",
    addressLine1 = "Plaza Rosal, 8",
    addressLine2 = "Plaza Rosal, 8"
  )
)

private val hotelPhotoUrls = listOf(
  "https://images.unsplash.com/photo-1455587734955-081b22074882?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1170&q=80",
  "https://images.unsplash.com/photo-1542314831-068cd1dbfeeb?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1170&q=80",
  "https://images.unsplash.com/photo-1496417263034-38ec4f0b665a?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1171&q=80",
  "https://images.unsplash.com/photo-1568084680786-a84f91d1153c?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=774&q=80",
  "https://images.unsplash.com/photo-1518733057094-95b53143d2a7?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=365&q=80",
  "https://images.unsplash.com/photo-1578683010236-d716f9a3f461?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1170&q=80",
  "https://images.unsplash.com/photo-1521783988139-89397d761dce?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=725&q=80",
  "https://images.unsplash.com/photo-1596436889106-be35e843f974?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1170&q=80",
  "https://images.unsplash.com/photo-1561384932-145921ce5214?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=435&q=80",
  "https://images.unsplash.com/photo-1554435517-12c44b0be193?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=435&q=80",
  "https://images.unsplash.com/photo-1474690455603-a369ec1293f9?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1170&q=80",
  "https://images.unsplash.com/photo-1455382054916-9c12746cfb43?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=465&q=80",
  "https://images.unsplash.com/photo-1445019980597-93fa8acb246c?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1174&q=80",
  "https://images.unsplash.com/photo-1596394516093-501ba68a0ba6?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1170&q=80"
)

private val roomPhotoUrls = listOf(
  "https://images.unsplash.com/photo-1611892440504-42a792e24d32?q=80&w=5070&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
  "https://images.unsplash.com/photo-1618773928121-c32242e63f39?q=80&w=4608&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
  "https://images.unsplash.com/photo-1596394516093-501ba68a0ba6?q=80&w=5070&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
  "https://images.unsplash.com/photo-1631049307264-da0ec9d70304?q=80&w=5070&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
  "https://images.unsplash.com/photo-1568495248636-6432b97bd949?q=80&w=4032&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
  "https://images.unsplash.com/photo-1590490360182-c33d57733427?q=80&w=4048&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
  "https://images.unsplash.com/flagged/photo-1556438758-8d49568ce18e?q=80&w=4288&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
  "https://images.unsplash.com/photo-1605346434674-a440ca4dc4c0?q=80&w=4000&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
  "https://images.unsplash.com/photo-1631049552057-403cdb8f0658?q=80&w=5070&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
  "https://images.unsplash.com/photo-1629140727571-9b5c6f6267b4?q=80&w=2160&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
  "https://images.unsplash.com/photo-1566665797739-1674de7a421a?q=80&w=4032&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
  "https://images.unsplash.com/photo-1578683010236-d716f9a3f461?q=80&w=5070&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
  "https://images.unsplash.com/photo-1616046229478-9901c5536a45?q=80&w=4096&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
  "https://images.unsplash.com/photo-1572987669554-0ba2ba9aee1f?q=80&w=5070&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
)

private fun randomHotelPhotoUrls(): List<String> {
  val count = Random.nextInt(from = 3, until = 10)
  val result = mutableSetOf<String>()
  repeat(count) {
    result.add(hotelPhotoUrls.random())
  }
  return result.toList()
}

private fun randomRoomPhotoUrls(): List<String> {
  val count = Random.nextInt(from = 3, until = 8)
  val result = mutableSetOf<String>()
  repeat(count) {
    result.add(roomPhotoUrls.random())
  }
  return result.toList()
}

private fun randomHotelInformation(): HotelDetails.HotelInformation {
  val numberOfBedrooms = Random.nextInt(from = 1, until = 5)
  val numberOfBathrooms = Random.nextInt(from = 1, until = numberOfBedrooms + 1)
  val squareMeters = Random.nextInt(from = 100, until = 350)
  return HotelDetails.HotelInformation(
    numberOfBedrooms = numberOfBedrooms,
    numberOfBathrooms = numberOfBathrooms,
    squareMeters = squareMeters
  )
}

private fun randomBookedDates(): List<String> {
  val startDate = LocalDate.now()
  val endDate = LocalDate.of(startDate.year + 1, 12, 31)
  val dateList = mutableListOf<String>()

  val numberOfDates =
    if (Random.nextDouble() > 0.3) Random.nextInt(50, 100) else Random.nextInt(10, 50)

  for (i in 1..numberOfDates) {
    val randomDaysToAdd = Random.nextInt(0, Period.between(startDate, endDate).days)
    val randomDate = startDate.plusDays(randomDaysToAdd.toLong())
    //TODO: Should not have access to presentation module extension function
    if (Random.nextDouble() < 0.3) {
      val rangeLength = Random.nextInt(2, 8)
      for (j in 0 until rangeLength) {
        val rangeDate = randomDate.plusDays(j.toLong())
        if (!rangeDate.isAfter(endDate)) {
          dateList.add(rangeDate.toDateString())
        }
      }
    } else {
      dateList.add(randomDate.toDateString())
    }
  }
  return dateList.distinct().sorted()
}


private fun randomTopSpots(): List<TopSpot> {
  val count = Random.nextInt(from = 3, until = 8)
  val result = mutableSetOf<TopSpot>()
  repeat(count) {
    result.add(topSpots.random())
  }
  return result.toList()
}

private val hotelFeatures = listOf(
  Amenity(
    "Air Conditioning",
    "Climate control in all rooms for your comfort.",
    AmenityIconResId.AIR_CONDITIONER
  ),
  Amenity(
    "Security Cameras",
    "24/7 surveillance for your safety and security.",
    AmenityIconResId.SECURITY_CAMERA
  ),
  Amenity(
    "Work Desk",
    "Spacious desks in every room for business travelers.",
    AmenityIconResId.DESK
  ),
  Amenity(
    "Events Space",
    "Facilities available for conferences and events.",
    AmenityIconResId.EVENTS
  ),
  Amenity(
    "Fire Safety",
    "Equipped with modern fire safety systems.",
    AmenityIconResId.FIRE
  ),
  Amenity(
    "First Aid",
    "First aid kits available for emergencies.",
    AmenityIconResId.FIRST_AID
  ),
  Amenity(
    "Fully Equipped Kitchen",
    "Suites with kitchens for extended stays.",
    AmenityIconResId.KITCHEN
  ),
  Amenity(
    "Parking Available",
    "Ample parking space for all guests.",
    AmenityIconResId.PARKING
  ),
  Amenity(
    "Swimming Pool",
    "Outdoor pool for relaxation and recreation.",
    AmenityIconResId.POOL
  ),
  Amenity(
    "Television",
    "Flat-screen TVs in every room with satellite channels.",
    AmenityIconResId.TV
  ),
  Amenity(
    "Wi-Fi",
    "Complimentary high-speed Wi-Fi across the hotel.",
    AmenityIconResId.WIFI
  )
)

private fun randomHotelFeatures(): List<Amenity> {
  val count = Random.nextInt(from = 4, until = hotelFeatures.size + 1)
  return hotelFeatures.take(count)
}

private fun randomReviews(): List<Review> {
  val count = Random.nextInt(from = 1, until = 15)
  val result = mutableListOf<Review>()
  repeat(count) {
    val review = reviews.random()
    val author = reviewAuthors.random()
    val votes = if (Random.nextInt(100) < 40) null else Random.nextInt(0, 501)
    result.add(
      Review(
        author = author,
        rating = review.first,
        text = review.second,
        votes = votes,
        created = randomDate()
      )
    )
  }
  return result
}

private val reviews = listOf(
  5 to "A truly exceptional stay. The staff went above and beyond to make our visit memorable. The amenities were top-notch.",
  4 to "Decent hotel with friendly staff. The restaurant could have more diverse options on the menu.",
  4 to "Lovely views and a relaxing ambiance. The swimming pool was a highlight.",
  5 to "Trendy and comfortable. The hotel facilities are excellent, especially the fitness center.",
  5 to "Great value for the location. The meeting room was well-equipped for my business needs.",
  5 to "Incredible ambiance and an attentive staff. The meeting room facilities were top-notch for our corporate event.",
  4 to "An exquisite stay with every detail meticulously taken care of. The restaurant's culinary offerings were a delight",
  3 to "Overpriced for the experience. The restaurant staff seemed disorganized, and the Wi-Fi barely worked during my stay.",
  2 to "Expected more from a luxury hotel. The fitness center lacked proper maintenance, and the room service was slow.",
  3 to "Disappointing stay overall. The view was the only highlight; the rest of the amenities felt outdated.",
  3 to "Misleading photos online. The hotel felt outdated, and the restaurant's food quality did not match the prices.",
  5 to "Central location and attentive staff. The hotel's modern design and facilities exceeded my expectations",
  5 to "Ideal for a short stay in the city. The convenience of having a well-equipped fitness center was a definite plus.",
  4 to "Stylish and compact, perfect for solo travelers. The 24/7 Wi-Fi access was a lifesaver for my work commitments.",
  5 to "A hidden gem by the riverbank. The hotel's serene atmosphere made my vacation truly memorable."
)

private val reviewAuthors = listOf(
  Review.Author(
    name = "Emily Johnson",
    avatarUrl = "https://images.unsplash.com/photo-1580489944761-15a19d654956?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=461&q=80"
  ),
  Review.Author(
    name = "Robert Harris",
    avatarUrl = "https://images.unsplash.com/photo-1543610892-0b1f7e6d8ac1?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=387&q=80"
  ),
  Review.Author(
    name = "Alex Turner",
    avatarUrl = "https://images.unsplash.com/photo-1566492031773-4f4e44671857?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=387&q=80"
  ),
  Review.Author(
    name = "Linda Parker",
    avatarUrl = "https://plus.unsplash.com/premium_photo-1677368597077-009727e906db?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=387&q=80"
  ),
  Review.Author(
    name = "Olivia Turner",
    avatarUrl = "https://images.unsplash.com/photo-1558898479-33c0057a5d12?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1170&q=80"
  )
)

private fun randomDate(): LocalDate {
  val year = Random.nextInt(from = 2021, until = 2025)
  val month = if (year == 2024) Random.nextInt(from = 1, until = 13)
  else Random.nextInt(from = 1, until = 13)
  val day = Random.nextInt(1, LocalDate.of(year, month, 1).lengthOfMonth() + 1)
  return LocalDate.of(year, month, day)
}

private fun randomRooms(): List<Room> {
  val roomCount = Random.nextInt(1, 11)
  return List(roomCount) {
    val images = randomRoomPhotoUrls()
    Room(
      basePrice = Random.nextDouble(50.0, 500.0),
      roomType = roomTypes.random(),
      thumbnailImage = images.first(),
      images = images
    )
  }
}

private val roomTypes = listOf(
  RoomType("Single", randomAmenities(), 1, 2),
  RoomType("Double", randomAmenities(), 2, 4),
  RoomType("Suite", randomAmenities(), 2, 5),
  RoomType("Deluxe Suite", randomAmenities(), 4, 6),
  RoomType("Family Room", randomAmenities(), 3, 6),
  RoomType("Penthouse", randomAmenities(), 4, 8)
)

private fun randomAmenities(): List<Amenity> {
  val amenities = listOf(
    Amenity("Wi-Fi", "High-speed internet access", AmenityIconResId.WIFI),
    Amenity("Air Conditioning", "Climate control in all rooms", AmenityIconResId.AIR_CONDITIONER),
    Amenity("TV", "Flat-screen TVs with satellite channels", AmenityIconResId.TV),
    Amenity("Desk", "Work desk with ergonomic chair", AmenityIconResId.DESK),
    Amenity("Kitchen", "Fully equipped kitchen", AmenityIconResId.KITCHEN),
    Amenity("Parking", "Complimentary parking space", AmenityIconResId.PARKING),
    Amenity("Fire Safety", "Equipped with modern fire safety systems", AmenityIconResId.FIRE)
  )
  return List(Random.nextInt(1, amenities.size + 1)) { amenities.random() }
}

private fun randomPrimaryContact(city: String): Hotel.HotelPrimaryContact {
  val user = randomUsers().random()
  val detail = if (Random.nextInt(100) < 10) null
  else randomHostDetails.random().replace("{name}", user.fullName).replace("{location}", city)

  return Hotel.HotelPrimaryContact(
    user = user,
    hostSinceDate = randomDate(),
    rating = Random.nextDouble(3.0, 5.0).roundTo(2),
    numberOfReviews = Random.nextInt(20, 1001),
    details = detail,
    isVerified = Random.nextBoolean()
  )
}

fun Double.roundTo(numFractionDigits: Int): Double {
  val factor = 10.0.pow(numFractionDigits.toDouble())
  return (this * factor).roundToInt() / factor
}

private val randomHostDetails = listOf(
  "Hi, my name is {name}. As a local enthusiast living in {location}, I'm excited to welcome you to one of the most breathtaking attractions in the area. Get ready for an unforgettable experience!",
  "Hello! I'm {name}, and I've been hosting guests in {location} for over 10 years. I love sharing the beauty of our attractions with visitors. Looking forward to hosting you soon!",
  "Greetings from {location}! My name is {name}, and I can't wait to show you around the stunning attractions we have here. Your adventure starts as soon as you arrive!",
  "Welcome to {location}, where I, {name}, will be your guide to exploring the best spots. From historical landmarks to natural wonders, let's make your stay memorable!",
  "I'm {name}, living in {location} and loving every minute of it. I enjoy introducing my guests to the local culture and hidden gems of the area. Join me for a unique journey through attractions.",
  "As a proud resident of {location} and an experienced host, I, {name}, am here to ensure your visit is comfortable and exciting. Explore the best of our attractions with my insider tips!",
  "Hey there, I'm {name} from {location}. Whether you're here for relaxation or adventure, I'll help you discover the perfect places in our region. Enjoy the charm of {location} with a personal touch!",
  "Hi, I'm {name}, your host in {location}. With years of experience in hospitality and a deep love for our attractions, I'm here to make your stay as enjoyable as possible.",
  "Welcome to {location}! I'm {name}, and I look forward to showing you why this place is loved by everyone who visits. From delicious local cuisine to scenic spots, let's explore it all together!",
  "Hi! {name} here, inviting you to experience the best of {location}. Dive into the local lifestyle and enjoy everything from our vibrant markets to peaceful attractions. Let's make great memories!"
)

private val avatarUrls = listOf(
  "https://images.unsplash.com/photo-1500648767791-00dcc994a43e?q=80&w=3000&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
  "https://images.unsplash.com/photo-1494790108377-be9c29b29330?q=80&w=3744&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
  "https://images.unsplash.com/photo-1535713875002-d1d0cf377fde?q=80&w=3648&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
  "https://images.unsplash.com/photo-1438761681033-6461ffad8d80?q=80&w=5070&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
  "https://images.unsplash.com/photo-1580489944761-15a19d654956?q=80&w=3974&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
  "https://images.unsplash.com/photo-1534528741775-53994a69daeb?q=80&w=3276&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
  "https://images.unsplash.com/photo-1544005313-94ddf0286df2?q=80&w=4912&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
  "https://images.unsplash.com/photo-1654110455429-cf322b40a906?q=80&w=3178&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
  "https://images.unsplash.com/photo-1501196354995-cbb51c65aaea?q=80&w=4883&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
  "https://images.unsplash.com/photo-1519419691348-3b3433c4c20e?q=80&w=2760&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
  "https://images.unsplash.com/photo-1439778615639-28529f7628bc?q=80&w=3648&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
)

private fun randomUsers(): List<User> {
  return listOf(
    User(null, "555-1234", "John", "Doe", avatarUrls.random(), Gender.MALE),
    User("jane.smith@example.com", "555-5678", "Jane", null, avatarUrls.random(), Gender.FEMALE),
    User("alex.jones@example.com", "555-9012", "Alex", null, avatarUrls.random(), null),
    User("sarah.connor@example.com", "555-3456", "Sarah", "Connor", null, Gender.FEMALE),
    User("mike.brown@example.com", null, "Mike", "Brown", avatarUrls.random(), Gender.MALE),
    User("lucy.white@example.com", "555-7878", "Lucy", null, avatarUrls.random(), Gender.FEMALE),
    User("sam.wilson@example.com", "555-2929", "Sam", "Wilson", avatarUrls.random(), null),
    User(null, "555-0101", "Olivia", "James", avatarUrls.random(), Gender.FEMALE),
    User("ethan.hunt@example.com", "555-4545", "Ethan", "Hunt", null, Gender.MALE),
    User("emma.thorne@example.com", null, "Emma", "Thorne", null, Gender.FEMALE),
    User(null, "555-3030", "Noah", "Clark", null, Gender.MALE),
    User("ava.taylor@example.com", "555-1212", "Ava", "Taylor", avatarUrls.random(), Gender.FEMALE),
    User("jack.murphy@example.com", null, "Jack", "Murphy", avatarUrls.random(), null),
    User("mia.knight@example.com", "555-5656", "Mia", "Knight", null, Gender.FEMALE),
  )
}



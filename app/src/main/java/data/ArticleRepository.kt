package com.example.nemopedia.data

import android.content.Context
import com.example.nemopedia.utils.PreferenceHelper
import com.example.nemopedia.model.Article

object ArticleRepository {

    private val articles = mutableListOf<Article>()
    private lateinit var preferenceHelper: PreferenceHelper

    init {
        loadArticles()
    }

    // Function untuk init PreferenceHelper
    fun init(context: Context) {
        preferenceHelper = PreferenceHelper(context)
    }

    private fun loadArticles() {
        articles.addAll(
            listOf(
                Article(
                    id = 1,
                    title = "Fotosintesis: Proses Kehidupan Tumbuhan",
                    category = "Sains",
                    summary = "Proses biokimia yang dilakukan tumbuhan untuk menghasilkan makanan menggunakan energi cahaya matahari.",
                    content = """
                        <h2>Apa itu Fotosintesis?</h2>
                        <p>Fotosintesis adalah proses biokimia yang dilakukan oleh tumbuhan, alga, dan beberapa jenis bakteri untuk mengubah energi cahaya matahari menjadi energi kimia dalam bentuk glukosa.</p>
                        
                        <h3>Proses Fotosintesis</h3>
                        <p>Fotosintesis terjadi dalam dua tahap utama:</p>
                        <p><b>1. Reaksi Terang:</b> Terjadi di tilakoid kloroplas, memerlukan cahaya untuk menghasilkan ATP dan NADPH.</p>
                        <p><b>2. Reaksi Gelap (Siklus Calvin):</b> Terjadi di stroma, menggunakan ATP dan NADPH untuk menghasilkan glukosa.</p>
                        
                        <h3>Persamaan Kimia</h3>
                        <p><i>6CO₂ + 6H₂O + Cahaya → C₆H₁₂O₆ + 6O₂</i></p>
                        
                        <h3>Pentingnya Fotosintesis</h3>
                        <p>• Menghasilkan oksigen yang kita hirup</p>
                        <p>• Menjadi sumber makanan bagi semua makhluk hidup</p>
                        <p>• Mengurangi CO₂ di atmosfer</p>
                        <p>• Menjaga keseimbangan ekosistem</p>
                        
                        <h3>Faktor yang Mempengaruhi</h3>
                        <p>• Intensitas cahaya</p>
                        <p>• Konsentrasi CO₂</p>
                        <p>• Suhu lingkungan</p>
                        <p>• Ketersediaan air</p>
                    """.trimIndent(),
                    readTimeMinutes = 5
                ),

                Article(
                    id = 2,
                    title = "Revolusi Industri 4.0",
                    category = "Teknologi",
                    summary = "Era transformasi digital yang mengintegrasikan teknologi cyber-physical dalam dunia industri.",
                    content = """
                        <h2>Revolusi Industri 4.0</h2>
                        <p>Revolusi Industri 4.0 adalah fase keempat dari revolusi industri yang ditandai dengan otomatisasi dan pertukaran data dalam teknologi manufaktur.</p>
                        
                        <h3>Teknologi Utama</h3>
                        <p><b>1. Internet of Things (IoT):</b> Jaringan perangkat yang terhubung dan berkomunikasi</p>
                        <p><b>2. Artificial Intelligence:</b> Kecerdasan buatan untuk automasi dan analisis</p>
                        <p><b>3. Big Data:</b> Analisis data dalam jumlah besar</p>
                        <p><b>4. Cloud Computing:</b> Komputasi berbasis cloud</p>
                        <p><b>5. Robotika:</b> Robot canggih untuk otomatisasi</p>
                        
                        <h3>Dampak Positif</h3>
                        <p>• Efisiensi produksi meningkat drastis</p>
                        <p>• Kualitas produk lebih baik dan konsisten</p>
                        <p>• Biaya produksi menurun</p>
                        <p>• Personalisasi produk massal</p>
                        
                        <h3>Tantangan</h3>
                        <p>• Pengangguran akibat otomatisasi</p>
                        <p>• Kesenjangan digital antar negara</p>
                        <p>• Keamanan dan privasi data</p>
                        <p>• Perlu skill dan pendidikan baru</p>
                        
                        <h3>Masa Depan</h3>
                        <p>Industri 4.0 terus berkembang menuju Industry 5.0 yang lebih fokus pada kolaborasi manusia-mesin dan keberlanjutan lingkungan.</p>
                    """.trimIndent(),
                    readTimeMinutes = 6
                ),

                Article(
                    id = 3,
                    title = "Kerajaan Majapahit",
                    category = "Sejarah",
                    summary = "Kerajaan Hindu-Buddha terbesar di Nusantara yang mencapai kejayaan pada abad ke-14.",
                    content = """
                        <h2>Kerajaan Majapahit</h2>
                        <p>Majapahit adalah kerajaan Hindu-Buddha yang berdiri tahun 1293-1527 M di Jawa Timur, Indonesia. Kerajaan ini menjadi yang terbesar dan terkuat di Nusantara.</p>
                        
                        <h3>Masa Kejayaan</h3>
                        <p>Majapahit mencapai puncak kejayaannya di bawah kepemimpinan <b>Raja Hayam Wuruk</b> (1350-1389) dengan Mahapatih <b>Gajah Mada</b>.</p>
                        
                        <h3>Sumpah Palapa</h3>
                        <p><i>"Lamun huwus kalah nusantara isun amukti palapa"</i></p>
                        <p>(Kalau sudah menguasai Nusantara, baru saya akan menikmati buah palapa)</p>
                        
                        <h3>Wilayah Kekuasaan</h3>
                        <p>Majapahit menguasai wilayah yang sangat luas meliputi:</p>
                        <p>• Pulau Jawa</p>
                        <p>• Sumatera</p>
                        <p>• Kalimantan</p>
                        <p>• Sulawesi</p>
                        <p>• Maluku</p>
                        <p>• Sebagian Semenanjung Malaya</p>
                        
                        <h3>Peninggalan Bersejarah</h3>
                        <p>• Candi Tikus</p>
                        <p>• Candi Bajang Ratu</p>
                        <p>• Situs Trowulan (bekas ibu kota)</p>
                        <p>• Kitab Negarakertagama</p>
                        <p>• Kitab Sutasoma (sumber Bhinneka Tunggal Ika)</p>
                        
                        <h3>Kemunduran</h3>
                        <p>Majapahit mulai melemah setelah wafatnya Hayam Wuruk, diperparah dengan perang saudara dan munculnya kesultanan-kesultanan Islam di pesisir Nusantara.</p>
                    """.trimIndent(),
                    readTimeMinutes = 7
                ),

                Article(
                    id = 4,
                    title = "Sistem Tata Surya",
                    category = "Sains",
                    summary = "Kumpulan benda langit yang terdiri dari Matahari dan semua objek yang mengorbit di sekitarnya.",
                    content = """
                        <h2>Sistem Tata Surya</h2>
                        <p>Tata Surya adalah sistem planet yang terdiri dari Matahari dan semua objek yang terikat oleh gravitasinya. Tata Surya terbentuk sekitar 4.6 miliar tahun yang lalu.</p>
                        
                        <h3>Struktur Tata Surya</h3>
                        <p><b>1. Matahari:</b> Bintang pusat dengan 99.86% massa tata surya</p>
                        <p><b>2. Planet Dalam:</b> Merkurius, Venus, Bumi, Mars</p>
                        <p><b>3. Sabuk Asteroid:</b> Antara Mars dan Jupiter</p>
                        <p><b>4. Planet Luar:</b> Jupiter, Saturnus, Uranus, Neptunus</p>
                        <p><b>5. Sabuk Kuiper:</b> Di luar orbit Neptunus (termasuk Pluto)</p>
                        
                        <h3>Planet-Planet</h3>
                        <p><b>Merkurius:</b> Planet terkecil dan terdekat dengan Matahari, permukaan penuh kawah</p>
                        <p><b>Venus:</b> Planet terpanas karena efek rumah kaca, atmosfer CO₂</p>
                        <p><b>Bumi:</b> Satu-satunya planet berpenghuni yang kita tahu</p>
                        <p><b>Mars:</b> Planet merah, target eksplorasi manusia</p>
                        <p><b>Jupiter:</b> Planet terbesar, Badai Merah Raksasa</p>
                        <p><b>Saturnus:</b> Memiliki cincin indah dari es dan batu</p>
                        <p><b>Uranus:</b> Berotasi miring hampir 90 derajat</p>
                        <p><b>Neptunus:</b> Planet terjauh, warna biru karena metana</p>
                        
                        <h3>Fakta Menarik</h3>
                        <p>• Matahari bisa menampung 1.3 juta Bumi</p>
                        <p>• Jupiter bisa menampung 1.300 Bumi</p>
                        <p>• Satu hari di Venus = 243 hari Bumi</p>
                        <p>• Mars memiliki gunung tertinggi: Olympus Mons (22 km)</p>
                    """.trimIndent(),
                    readTimeMinutes = 6
                ),

                Article(
                    id = 5,
                    title = "Artificial Intelligence (AI)",
                    category = "Teknologi",
                    summary = "Simulasi kecerdasan manusia yang diprogram dalam mesin untuk berpikir dan belajar.",
                    content = """
                        <h2>Artificial Intelligence</h2>
                        <p>AI adalah cabang ilmu komputer yang bertujuan menciptakan mesin yang dapat meniru kecerdasan manusia, seperti belajar, bernalar, dan memecahkan masalah.</p>
                        
                        <h3>Jenis-Jenis AI</h3>
                        <p><b>1. Narrow AI (Weak AI):</b> AI yang dirancang untuk tugas spesifik seperti Siri, AlphaGo, atau pengenalan wajah.</p>
                        <p><b>2. General AI (Strong AI):</b> AI dengan kemampuan kognitif setara manusia. Belum ada yang berhasil dibuat.</p>
                        <p><b>3. Super AI:</b> AI yang melampaui kecerdasan manusia. Masih teoretis.</p>
                        
                        <h3>Teknologi AI</h3>
                        <p><b>Machine Learning:</b> Mesin belajar dari data tanpa diprogram eksplisit</p>
                        <p><b>Deep Learning:</b> Neural network berlapis dalam</p>
                        <p><b>Natural Language Processing:</b> Pemahaman bahasa manusia</p>
                        <p><b>Computer Vision:</b> Pengenalan dan analisis gambar</p>
                        <p><b>Robotics:</b> AI yang diterapkan pada robot fisik</p>
                        
                        <h3>Aplikasi AI dalam Kehidupan</h3>
                        <p>• Asisten virtual: Siri, Alexa, Google Assistant</p>
                        <p>• Rekomendasi: Netflix, YouTube, Spotify</p>
                        <p>• Kendaraan otonom (self-driving cars)</p>
                        <p>• Diagnosis medis</p>
                        <p>• Deteksi fraud di perbankan</p>
                        <p>• Pengenalan wajah untuk keamanan</p>
                        <p>• Chatbot customer service</p>
                        
                        <h3>Tantangan Etika AI</h3>
                        <p>• Bias dalam algoritma dan data</p>
                        <p>• Privacy dan keamanan data pribadi</p>
                        <p>• Pengangguran akibat otomasi</p>
                        <p>• Penggunaan AI untuk senjata</p>
                        <p>• Tanggung jawab atas keputusan AI</p>
                        
                        <h3>Masa Depan AI</h3>
                        <p>AI terus berkembang pesat dan diprediksi akan mengubah hampir semua aspek kehidupan manusia dalam 10-20 tahun ke depan.</p>
                    """.trimIndent(),
                    readTimeMinutes = 7
                ),

                Article(
                    id = 6,
                    title = "Sel: Unit Terkecil Kehidupan",
                    category = "Biologi",
                    summary = "Sel adalah unit struktural dan fungsional terkecil dari semua organisme hidup.",
                    content = """
                        <h2>Sel: Dasar Kehidupan</h2>
                        <p>Sel adalah unit dasar kehidupan, baik struktural maupun fungsional dari semua makhluk hidup. Semua organisme tersusun dari satu atau lebih sel.</p>
                        
                        <h3>Teori Sel</h3>
                        <p><b>1.</b> Semua makhluk hidup tersusun dari satu atau lebih sel</p>
                        <p><b>2.</b> Sel adalah unit dasar kehidupan</p>
                        <p><b>3.</b> Semua sel berasal dari sel yang sudah ada sebelumnya (omnis cellula e cellula)</p>
                        
                        <h3>Jenis Sel</h3>
                        <p><b>Sel Prokariotik:</b></p>
                        <p>• Tidak memiliki nukleus sejati</p>
                        <p>• DNA berada di nukleoid</p>
                        <p>• Tidak punya organel bermembran</p>
                        <p>• Contoh: Bakteri, Arkea</p>
                        
                        <p><b>Sel Eukariotik:</b></p>
                        <p>• Memiliki nukleus sejati dengan membran</p>
                        <p>• Punya organel bermembran lengkap</p>
                        <p>• Lebih kompleks dan besar</p>
                        <p>• Contoh: Hewan, Tumbuhan, Jamur, Protista</p>
                        
                        <h3>Organel Sel Eukariotik</h3>
                        <p><b>Nukleus:</b> Pusat kendali sel, menyimpan DNA</p>
                        <p><b>Mitokondria:</b> Pembangkit tenaga (ATP)</p>
                        <p><b>Ribosom:</b> Tempat sintesis protein</p>
                        <p><b>RE (Retikulum Endoplasma):</b> Transport molekul</p>
                        <p><b>Aparatus Golgi:</b> Modifikasi dan pengemasan protein</p>
                        <p><b>Lisosom:</b> Pencernaan intraseluler</p>
                        <p><b>Kloroplas:</b> Fotosintesis (khusus tumbuhan)</p>
                        <p><b>Vakuola:</b> Penyimpanan (besar pada tumbuhan)</p>
                        
                        <h3>Fakta Menarik</h3>
                        <p>• Tubuh manusia terdiri dari 37.2 triliun sel</p>
                        <p>• Sel darah merah hidup sekitar 120 hari</p>
                        <p>• Neuron (sel saraf) bisa hidup seumur hidup</p>
                        <p>• Sel terbesar adalah telur burung unta</p>
                        <p>• Sel terkecil adalah bakteri Mycoplasma</p>
                    """.trimIndent(),
                    readTimeMinutes = 6
                ),

                Article(
                    id = 7,
                    title = "Benua dan Samudra di Bumi",
                    category = "Geografi",
                    summary = "Pembagian daratan dan perairan di permukaan Bumi yang membentuk tujuh benua dan lima samudra.",
                    content = """
                        <h2>Benua dan Samudra</h2>
                        <p>Permukaan Bumi terdiri dari 29% daratan (benua dan pulau) dan 71% perairan (samudra dan laut).</p>
                        
                        <h3>Tujuh Benua (dari terbesar)</h3>
                        <p><b>1. Asia (44.58 juta km²):</b></p>
                        <p>• Benua terbesar dengan populasi terbanyak (4.7 miliar)</p>
                        <p>• Gunung tertinggi: Everest (8.849 m)</p>
                        <p>• Negara terbesar: Rusia (sebagian di Eropa)</p>
                        
                        <p><b>2. Afrika (30.37 juta km²):</b></p>
                        <p>• Benua terpanas, gurun Sahara terbesar</p>
                        <p>• Sungai terpanjang: Nil (6.650 km)</p>
                        <p>• Fauna unik: Singa, gajah, jerapah</p>
                        
                        <p><b>3. Amerika Utara (24.71 juta km²):</b></p>
                        <p>• Negara maju: AS, Kanada, Meksiko</p>
                        <p>• Grand Canyon, Air Terjun Niagara</p>
                        
                        <p><b>4. Amerika Selatan (17.84 juta km²):</b></p>
                        <p>• Hutan Amazon terbesar di dunia</p>
                        <p>• Sungai Amazon (terpanjang ke-2)</p>
                        <p>• Pegunungan Andes terpanjang</p>
                        
                        <p><b>5. Antartika (14.2 juta km²):</b></p>
                        <p>• Benua es, tidak berpenghuni tetap</p>
                        <p>• Menyimpan 90% es dunia</p>
                        <p>• Suhu terendah: -89.2°C</p>
                        
                        <p><b>6. Eropa (10.18 juta km²):</b></p>
                        <p>• Benua paling maju secara ekonomi</p>
                        <p>• Banyak negara kecil</p>
                        <p>• Sejarah peradaban kaya</p>
                        
                        <p><b>7. Australia/Oseania (8.56 juta km²):</b></p>
                        <p>• Benua terkecil</p>
                        <p>• Fauna unik: kanguru, koala</p>
                        <p>• Great Barrier Reef (terumbu karang terbesar)</p>
                        
                        <h3>Lima Samudra</h3>
                        <p><b>1. Samudra Pasifik:</b> Terbesar (165.2 juta km²), Palung Mariana terdalam</p>
                        <p><b>2. Samudra Atlantik:</b> Kedua terbesar (106.4 juta km²)</p>
                        <p><b>3. Samudra Hindia:</b> Terhangat (70.56 juta km²)</p>
                        <p><b>4. Samudra Selatan:</b> Mengelilingi Antartika (20.33 juta km²)</p>
                        <p><b>5. Samudra Arktik:</b> Terkecil, sebagian beku (14.06 juta km²)</p>
                        
                        <h3>Teori Lempeng Tektonik</h3>
                        <p>Benua-benua terus bergerak perlahan karena aktivitas lempeng tektonik. Dahulu semua benua bersatu dalam superbenua <b>Pangaea</b> sekitar 300 juta tahun lalu, lalu pecah menjadi benua-benua saat ini.</p>
                    """.trimIndent(),
                    readTimeMinutes = 6
                ),

                Article(
                    id = 8,
                    title = "Seni Lukis Renaissance",
                    category = "Seni",
                    summary = "Gerakan seni yang berkembang di Eropa abad 14-17, menandai kebangkitan kembali seni klasik.",
                    content = """
                        <h2>Seni Lukis Renaissance</h2>
                        <p>Renaissance (dalam bahasa Italia berarti "kelahiran kembali") adalah periode kebangkitan seni, budaya, dan ilmu pengetahuan di Eropa, dimulai dari Italia pada abad ke-14.</p>
                        
                        <h3>Ciri-Ciri Seni Renaissance</h3>
                        <p>• <b>Humanisme:</b> Fokus pada manusia dan kehidupan duniawi</p>
                        <p>• <b>Perspektif:</b> Teknik menggambar ruang 3 dimensi</p>
                        <p>• <b>Realisme:</b> Penggambaran anatomi manusia akurat</p>
                        <p>• <b>Chiaroscuro:</b> Teknik kontras cahaya-gelap</p>
                        <p>• <b>Sfumato:</b> Transisi warna halus tanpa garis tegas</p>
                        
                        <h3>Pelukis Terkenal Renaissance</h3>
                        <p><b>Leonardo da Vinci (1452-1519):</b></p>
                        <p>• Mona Lisa (lukisan paling terkenal di dunia)</p>
                        <p>• The Last Supper (Perjamuan Terakhir)</p>
                        <p>• Vitruvian Man</p>
                        <p>• Juga seorang penemu, ilmuwan, dan insinyur</p>
                        
                        <p><b>Michelangelo (1475-1564):</b></p>
                        <p>• Langit-langit Kapel Sistina</p>
                        <p>• Patung David</p>
                        <p>• The Creation of Adam</p>
                        <p>• Seniman paling berpengaruh dalam sejarah</p>
                        
                        <p><b>Raphael (1483-1520):</b></p>
                        <p>• The School of Athens</p>
                        <p>• Madonna paintings</p>
                        <p>• Dikenal dengan komposisi harmonis</p>
                        
                        <p><b>Sandro Botticelli (1445-1510):</b></p>
                        <p>• The Birth of Venus</p>
                        <p>• Primavera</p>
                        <p>• Gaya anggun dan liris</p>
                        
                        <h3>Dampak Renaissance</h3>
                        <p>• Mengubah cara pandang terhadap seni dan kemanusiaan</p>
                        <p>• Mendorong perkembangan ilmu pengetahuan</p>
                        <p>• Mempengaruhi arsitektur, sastra, dan musik</p>
                        <p>• Menjadi fondasi seni modern</p>
                        
                        <h3>Warisan Renaissance</h3>
                        <p>Teknik dan prinsip seni Renaissance masih dipelajari dan diterapkan hingga saat ini. Karya-karya dari periode ini menjadi koleksi museum paling berharga di dunia.</p>
                    """.trimIndent(),
                    readTimeMinutes = 7
                ),

                Article(
                    id = 9,
                    title = "Teori Evolusi Darwin",
                    category = "Biologi",
                    summary = "Teori yang menjelaskan bagaimana spesies berubah dan beradaptasi melalui seleksi alam.",
                    content = """
                        <h2>Teori Evolusi Charles Darwin</h2>
                        <p>Teori evolusi adalah salah satu teori paling penting dalam biologi, yang menjelaskan bagaimana spesies berkembang dan berubah sepanjang waktu melalui proses seleksi alam.</p>
                        
                        <h3>Charles Darwin (1809-1882)</h3>
                        <p>Darwin adalah naturalis Inggris yang mengembangkan teori evolusi setelah perjalanan 5 tahun di HMS Beagle, terutama saat mengamati burung finch di Kepulauan Galápagos.</p>
                        
                        <h3>Prinsip Utama Teori Evolusi</h3>
                        <p><b>1. Variasi:</b></p>
                        <p>• Individu dalam populasi memiliki variasi sifat</p>
                        <p>• Variasi ini bisa diwariskan ke keturunan</p>
                        
                        <p><b>2. Seleksi Alam:</b></p>
                        <p>• Individu dengan sifat menguntungkan lebih mungkin bertahan</p>
                        <p>• "Survival of the fittest" (yang paling fit bertahan)</p>
                        <p>• Sifat menguntungkan diwariskan ke generasi berikutnya</p>
                        
                        <p><b>3. Adaptasi:</b></p>
                        <p>• Populasi berubah seiring waktu</p>
                        <p>• Organisme menjadi lebih cocok dengan lingkungannya</p>
                        
                        <p><b>4. Spesiasi:</b></p>
                        <p>• Spesies baru terbentuk dari populasi yang terisolasi</p>
                        <p>• Akumulasi perubahan menghasilkan spesies berbeda</p>
                        
                        <h3>Bukti Evolusi</h3>
                        <p><b>Fosil:</b> Menunjukkan perubahan bentuk organisme sepanjang waktu</p>
                        <p><b>Anatomi Komparatif:</b> Struktur homolog pada spesies berbeda</p>
                        <p><b>Embriologi:</b> Kesamaan perkembangan embrio</p>
                        <p><b>DNA:</b> Kesamaan genetik menunjukkan hubungan evolusi</p>
                        <p><b>Biogeografi:</b> Distribusi spesies mendukung evolusi</p>
                        
                        <h3>Contoh Evolusi</h3>
                        <p>• <b>Burung Finch Galápagos:</b> Bentuk paruh berbeda sesuai makanan</p>
                        <p>• <b>Ngengat Peppered:</b> Warna berubah mengikuti polusi industri</p>
                        <p>• <b>Resistensi Antibiotik:</b> Bakteri beradaptasi dengan obat</p>
                        
                        <h3>Evolusi vs Kreacionisme</h3>
                        <p>Teori evolusi adalah penjelasan ilmiah yang didukung bukti empiris. Meski ada perdebatan filosofis dan religius, evolusi adalah konsensus ilmiah yang diterima luas.</p>
                        
                        <h3>Dampak Teori Evolusi</h3>
                        <p>• Merevolusi pemahaman biologi</p>
                        <p>• Dasar untuk biologi modern, kedokteran, dan pertanian</p>
                        <p>• Membantu memahami penyebaran penyakit</p>
                        <p>• Penting untuk konservasi biodiversitas</p>
                    """.trimIndent(),
                    readTimeMinutes = 8
                ),

                Article(
                    id = 10,
                    title = "Peradaban Mesir Kuno",
                    category = "Sejarah",
                    summary = "Salah satu peradaban tertua dan paling maju di dunia yang berkembang di sepanjang Sungai Nil.",
                    content = """
                        <h2>Peradaban Mesir Kuno</h2>
                        <p>Mesir Kuno adalah peradaban yang berkembang di sepanjang Sungai Nil dari sekitar 3100 SM hingga 30 SM, ketika ditaklukkan oleh Romawi.</p>
                        
                        <h3>Periode Sejarah Mesir</h3>
                        <p><b>Kerajaan Lama (2686-2181 SM):</b></p>
                        <p>• Era pembangunan piramida besar</p>
                        <p>• Piramida Giza dan Sphinx</p>
                        <p>• Zaman keemasan arsitektur</p>
                        
                        <p><b>Kerajaan Tengah (2055-1650 SM):</b></p>
                        <p>• Periode kebangkitan setelah perpecahan</p>
                        <p>• Perkembangan seni dan sastra</p>
                        <p>• Ekspansi ke Nubia</p>
                        
                        <p><b>Kerajaan Baru (1550-1077 SM):</b></p>
                        <p>• Era paling makmur dan kuat</p>
                        <p>• Firaun terkenal: Tutankhamun, Ramses II, Cleopatra</p>
                        <p>• Lembah Para Raja</p>
                        
                        <h3>Pencapaian Mesir Kuno</h3>
                        <p><b>Arsitektur:</b></p>
                        <p>• Piramida Giza (satu dari 7 Keajaiban Dunia Kuno)</p>
                        <p>• Kuil Karnak dan Luxor</p>
                        <p>• Abu Simbel</p>
                        
                        <p><b>Sistem Tulisan:</b></p>
                        <p>• Hieroglif (tulisan suci)</p>
                        <p>• Papirus sebagai media tulis</p>
                        <p>• Batu Rosetta (kunci terjemahan hieroglif)</p>
                        
                        <p><b>Ilmu Pengetahuan:</b></p>
                        <p>• Matematika (geometri untuk arsitektur)</p>
                        <p>• Astronomi (kalender 365 hari)</p>
                        <p>• Kedokteran (mumifikasi, bedah)</p>
                        
                        <h3>Agama dan Kepercayaan</h3>
                        <p>• Politeisme (banyak dewa)</p>
                        <p>• Dewa utama: Ra (matahari), Osiris (akhirat), Isis, Anubis</p>
                        <p>• Firaun dianggap dewa hidup</p>
                        <p>• Kepercayaan kehidupan setelah mati</p>
                        <p>• Mumifikasi untuk mengawetkan tubuh</p>
                        
                        <h3>Firaun Terkenal</h3>
                        <p><b>Tutankhamun:</b> Makamnya ditemukan utuh, penuh harta karun</p>
                        <p><b>Ramses II:</b> Firaun terhebat, memerintah 66 tahun</p>
                        <p><b>Cleopatra VII:</b> Firaun terakhir, cerdas dan cantik</p>
                        <p><b>Hatshepsut:</b> Firaun perempuan yang kuat</p>
                        
                        <h3>Warisan</h3>
                        <p>Mesir Kuno memberikan warisan besar bagi peradaban dunia dalam arsitektur, seni, tulisan, dan ilmu pengetahuan yang masih dipelajari hingga kini.</p>
                    """.trimIndent(),
                    readTimeMinutes = 8
                ),

                Article(
                    id = 11,
                    title = "Mengapa Langit Berwarna Biru?",
                    category = "Pengetahuan Umum",
                    summary = "Penjelasan ilmiah mengapa langit terlihat berwarna biru di siang hari.",
                    content = """
                        <h2>Mengapa Langit Berwarna Biru?</h2>
                        <p>Langit berwarna biru karena fenomena yang disebut <b>Rayleigh Scattering</b> (hamburan Rayleigh).</p>
                        
                        <h3>Proses Hamburan Cahaya</h3>
                        <p>Cahaya matahari terdiri dari berbagai warna dengan panjang gelombang berbeda. Ketika cahaya memasuki atmosfer Bumi:</p>
                        <p>• Cahaya bertemu dengan molekul gas (nitrogen, oksigen)</p>
                        <p>• Cahaya biru memiliki panjang gelombang pendek</p>
                        <p>• Cahaya merah memiliki panjang gelombang panjang</p>
                        <p>• Cahaya biru lebih mudah dihamburkan ke segala arah</p>
                        
                        <h3>Mengapa Biru, Bukan Ungu?</h3>
                        <p>Sebenarnya cahaya ungu juga dihamburkan, tapi:</p>
                        <p>• Mata manusia lebih sensitif terhadap warna biru</p>
                        <p>• Matahari memancarkan lebih sedikit cahaya ungu</p>
                        <p>• Sebagian cahaya ungu diserap oleh atmosfer atas</p>
                        
                        <h3>Fenomena Terkait</h3>
                        <p><b>Matahari Terbenam (Sunset):</b><br>
                        Langit jadi merah/oranye karena cahaya melewati lebih banyak atmosfer, sehingga cahaya biru terhamburkan habis, yang tersisa cahaya merah.</p>
                        
                        <p><b>Langit di Bulan:</b><br>
                        Langit di bulan selalu hitam karena tidak ada atmosfer untuk menghamburkan cahaya.</p>
                        
                        <h3>Fakta Menarik</h3>
                        <p>• Langit di Mars berwarna pink kemerahan karena debu di atmosfernya</p>
                        <p>• Laut terlihat biru sebagian karena memantulkan warna langit</p>
                        <p>• Semakin tinggi ketinggian, langit semakin gelap (mendekati hitam)</p>
                    """.trimIndent(),
                    readTimeMinutes = 5
                ),

                Article(
                    id = 12,
                    title = "Cara Kerja WiFi",
                    category = "Pengetahuan Umum",
                    summary = "Penjelasan sederhana tentang bagaimana WiFi menghubungkan perangkat kita ke internet.",
                    content = """
                        <h2>Cara Kerja WiFi</h2>
                        <p>WiFi adalah teknologi yang memungkinkan perangkat terhubung ke internet tanpa kabel menggunakan gelombang radio.</p>
                        
                        <h3>Komponen Utama WiFi</h3>
                        <p><b>1. Router WiFi:</b></p>
                        <p>• Perangkat yang menerima sinyal internet dari modem</p>
                        <p>• Mengubah data menjadi gelombang radio</p>
                        <p>• Memancarkan sinyal ke area sekitar</p>
                        
                        <p><b>2. Adapter WiFi:</b></p>
                        <p>• Chip di laptop, HP, tablet</p>
                        <p>• Menerima dan menerjemahkan gelombang radio</p>
                        <p>• Mengubah kembali jadi data digital</p>
                        
                        <h3>Proses Koneksi WiFi</h3>
                        <p>1. Router memancarkan SSID (nama WiFi)</p>
                        <p>2. Perangkat mendeteksi sinyal WiFi yang tersedia</p>
                        <p>3. User memilih WiFi dan masukkan password</p>
                        <p>4. Router memverifikasi password</p>
                        <p>5. Data dikirim bolak-balik via gelombang radio</p>
                        
                        <h3>Frekuensi WiFi</h3>
                        <p><b>2.4 GHz:</b></p>
                        <p>• Jangkauan lebih luas</p>
                        <p>• Kecepatan lebih lambat</p>
                        <p>• Banyak gangguan (microwave, bluetooth)</p>
                        
                        <p><b>5 GHz:</b></p>
                        <p>• Jangkauan lebih pendek</p>
                        <p>• Kecepatan lebih cepat</p>
                        <p>• Lebih sedikit gangguan</p>
                        
                        <h3>Generasi WiFi</h3>
                        <p>• WiFi 4 (802.11n): sampai 600 Mbps</p>
                        <p>• WiFi 5 (802.11ac): sampai 3.5 Gbps</p>
                        <p>• WiFi 6 (802.11ax): sampai 9.6 Gbps</p>
                        <p>• WiFi 7: teknologi terbaru, lebih cepat lagi</p>
                        
                        <h3>Tips WiFi Lebih Cepat</h3>
                        <p>• Taruh router di tengah ruangan</p>
                        <p>• Hindari penghalang (tembok tebal, logam)</p>
                        <p>• Update firmware router secara berkala</p>
                        <p>• Gunakan frekuensi 5 GHz untuk streaming</p>
                        <p>• Batasi jumlah perangkat yang terkoneksi</p>
                    """.trimIndent(),
                    readTimeMinutes = 6
                )
            )
        )
    }

    // Function untuk mendapatkan semua artikel
    fun getArticles(): List<Article> {
        return articles
    }

    // Function untuk search artikel
    fun searchArticles(query: String): List<Article> {
        if (query.isBlank()) return articles

        return articles.filter {
            it.title.contains(query, ignoreCase = true) ||
                    it.summary.contains(query, ignoreCase = true) ||
                    it.content.contains(query, ignoreCase = true)
        }
    }

    // Function untuk filter berdasarkan kategori
    fun filterByCategory(category: String): List<Article> {
        if (category == "Semua") return articles
        return articles.filter { it.category.equals(category, ignoreCase = true) }
    }

    // Function untuk mendapatkan artikel berdasarkan ID
    fun getArticleById(id: Int): Article? {
        return articles.find { it.id == id }
    }

    // Function untuk toggle bookmark
    fun toggleBookmark(articleId: Int) {
        articles.find { it.id == articleId }?.let {
            it.isBookmarked = !it.isBookmarked
        }
    }

    // Function untuk mendapatkan artikel yang di-bookmark
    fun getBookmarkedArticles(): List<Article> {
        return articles.filter { it.isBookmarked }
    }

    // Function untuk mendapatkan semua kategori FIXED
    fun getAllCategories(): List<String> {
        val categories = if (::preferenceHelper.isInitialized) {
            preferenceHelper.getCategories()
        } else {
            listOf("Sains", "Sejarah", "Teknologi", "Seni", "Geografi", "Biologi", "Pengetahuan Umum")
        }
        return listOf("Semua") + categories.sorted()
    }

    // Function untuk mendapatkan kategori tanpa "Semua" (untuk spinner)
    fun getCategoriesForSpinner(): List<String> {
        return if (::preferenceHelper.isInitialized) {
            preferenceHelper.getCategories().sorted()
        } else {
            listOf("Sains", "Sejarah", "Teknologi", "Seni", "Geografi", "Biologi", "Pengetahuan Umum")
        }
    }

    // Function untuk menambah artikel baru
    fun addArticle(article: Article) {
        articles.add(article)
    }

    // BARU: Function untuk update artikel (hanya artikel user-created)
    fun updateArticle(updatedArticle: Article): Boolean {
        val index = articles.indexOfFirst { it.id == updatedArticle.id }

        if (index != -1) {
            val existingArticle = articles[index]

            // Hanya artikel user-created yang bisa diupdate
            if (!existingArticle.isUserCreated) {
                return false
            }

            articles[index] = updatedArticle
            return true
        }
        return false
    }

    // BARU: Function untuk menghapus artikel (hanya artikel user-created)
    fun deleteArticle(articleId: Int): Boolean {
        val article = articles.find { it.id == articleId }
        return if (article != null && article.isUserCreated) {
            articles.remove(article)
            true
        } else {
            false
        }
    }

    // BARU: Function untuk menghitung total artikel per kategori
    fun getArticleCountByCategory(category: String): Int {
        return if (category == "Semua") {
            articles.size
        } else {
            articles.count { it.category.equals(category, ignoreCase = true) }
        }
    }

    // BARU: Function untuk sort artikel
    enum class SortType {
        TITLE_ASC,      // A-Z
        TITLE_DESC,     // Z-A
        READ_TIME_ASC,  // Tercepat
        READ_TIME_DESC, // Terlama
        NEWEST,         // Terbaru (by ID)
        OLDEST          // Terlama (by ID)
    }

    fun getSortedArticles(sortType: SortType): List<Article> {
        return when (sortType) {
            SortType.TITLE_ASC -> articles.sortedBy { it.title }
            SortType.TITLE_DESC -> articles.sortedByDescending { it.title }
            SortType.READ_TIME_ASC -> articles.sortedBy { it.readTimeMinutes }
            SortType.READ_TIME_DESC -> articles.sortedByDescending { it.readTimeMinutes }
            SortType.NEWEST -> articles.sortedByDescending { it.id }
            SortType.OLDEST -> articles.sortedBy { it.id }
        }
    }

    // BARU: Function untuk mendapatkan recently viewed articles
    fun getRecentlyViewedArticles(): List<Article> {
        if (!::preferenceHelper.isInitialized) return emptyList()

        val recentIds = preferenceHelper.getRecentlyViewed()
        return recentIds.mapNotNull { id -> articles.find { it.id == id } }
    }

    // BARU: Function untuk menambah artikel ke recently viewed
    fun addToRecentlyViewed(articleId: Int) {
        if (::preferenceHelper.isInitialized) {
            preferenceHelper.addRecentlyViewed(articleId)
        }
    }
}
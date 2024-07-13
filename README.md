# 📚 Aplikasi Perpustakaan

Selamat datang di Aplikasi Perpustakaan! 
Aplikasi ini dirancang untuk melakukan berbagai operasi seperti mencatat peminjaman buku, mengelola koleksi buku, menambahkan data anggota perpustakaan, dan mengelola pengembalian buku.
Aplikasi ini dikembangkan menggunakan IntelliJ IDEA.

## 🎨 Fitur Utama
- **Transaksi Peminjaman & Pengembalian:**
  - Penambahan buku baru ke dalam database.
  - Pengeditan data buku yang sudah ada.
  - Penghapusan buku yang tidak lagi tersedia.
  - Pencarian buku berdasarkan judul dan penulis.
    
- **Manajemen Anggota:**
  - Penambahan data anggota baru.
  - Pengeditan data anggota yang sudah ada.
  - Penghapusan data anggota.
  - Pencarian anggota berdasarkan nama atau Id anggota.

- **Manajemen Pustakawan:**
  - Penambahan data pustakawan baru.
  - Pengeditan data pustakawan yang sudah ada.
  - Penghapusan data pustakawan.
  - Pencarian anggota berdasarkan nama atau Id pustakawan.
    
- **Transaksi Peminjaman & Pengembalian:**
  - Pencatatan transaksi peminjaman buku
  - Penghitungan tanggal jatuh tempo pengembalian buku
  - Pencatatan informasi anggota pada saat peminjaman
  - Pencatatan pengembalian buku
  - Pembaruan status buku
    
## 🛠️ Instalasi
Langkah-langkah berikut untuk mengatur dan menjalankan aplikasi:

1. **Clone Repository:**
    ```bash
    git clone https://github.com/username/library-app.git
    cd library-app
    ```
2. **Install Dependencies:**
    Pastikan Anda telah menginstal JDK 21 dan JavaFX. Tambahkan JavaFX ke path Anda.
3. **Setup Database:**
   Buat database MySQL dan jalankan skrip berikut untuk membuat tabel `buku`:
    ```sql
    CREATE TABLE buku (
      idbuku int(11) PRIMARY KEY,
      judul varchar(50),
      penerbi` varchar(50),
      penulis varchar(50),
      tahun_terbit int(11)
    );
    ```

    Buat database MySQL dan jalankan skrip berikut untuk membuat tabel `anggota`:
    ```sql
    CREATE TABLE anggota (
      idanggota int(11) PRIMARY KEY,
      nama varchar(50),
      alamat varchar(50)
    );
    ```

    Buat database MySQL dan jalankan skrip berikut untuk membuat tabel `pustakawan`:
    ```sql
    CREATE TABLE pustakawan (
      idpustakawan int(11) PRIMARY KEY,
      nama varchar(50),
      email varchar(20)
    );
    ```
    
    Buat database MySQL dan jalankan skrip berikut untuk membuat tabel `transaksi`:
    ```sql
    CREATE TABLE transaksi (
      idtransaksi int(11) NOT NULL,
      idanggota int(11) NOT NULL,
      idbuku int(11) NOT NULL,
      tanggalpinjam date NOT NULL,
      tanggaljatuhtempo date NOT NULL,
      tanggalkembali date DEFAULT NULL,
      status varchar(20) NOT NULL
    );
    ```

5. **Konfigurasi Database:**

6. **Jalankan Aplikasi:**
    Buka proyek di IntelliJ IDEA dan jalankan aplikasi.

## 💻 Penggunaan

### Tampilan Buku 📗

#### Menambahkan Buku

1. Klik tombol `Add` untuk memulai proses penambahan buku baru.
2. Isi detail buku pada kolom yang tersedia: ID Buku, Judul, Penerbit, Penulis, dan Tahun Terbit.
3. Klik tombol `Update` untuk menyimpan buku baru.

#### Mengedit Buku

1. Pilih buku yang ingin diedit dari tabel.
2. Klik tombol `Edit`.
3. Ubah detail buku yang diinginkan.
4. Klik tombol `Update` untuk menyimpan perubahan.

#### Menghapus Buku

1. Pilih buku yang ingin dihapus dari tabel.
2. Klik tombol `Del`.
3. Konfirmasi penghapusan pada dialog yang muncul.

#### Membatalkan Proses

1. Klik tombol `Cancel` untuk membatalkan proses penambahan atau pengeditan buku.

#### Memilih Buku dari Tabel

1. Klik pada salah satu baris buku di tabel untuk melihat detail buku tersebut pada form input.

#### Mencari Buku

1. Gunakan kolom pencarian untuk mencari buku berdasarkan ID, judul, atau penulis.
2. Tabel akan menampilkan hasil pencarian secara dinamis.
   ![image](https://github.com/user-attachments/assets/4ccea29f-81b3-476f-92b6-20c784510ac4)


### Tampilan Anggota 🧑🏽‍🤝‍👩🏻

#### Menambahkan Anggota

1. **Klik tombol `Add`** untuk memulai proses penambahan anggota baru.
2. Isi detail anggota pada kolom yang tersedia:
   - **ID Anggota**
   - **Nama**
   - **Alamat**
3. **Klik tombol `Update`** untuk menyimpan anggota baru.

#### Mengedit Anggota

1. Pilih anggota yang ingin diedit dari tabel.
2. **Klik tombol `Edit`**.
3. Ubah detail anggota yang diinginkan.
4. **Klik tombol `Update`** untuk menyimpan perubahan.

#### Menghapus Anggota

1. Pilih anggota yang ingin dihapus dari tabel.
2. **Klik tombol `Del`**.
3. Konfirmasi penghapusan pada dialog yang muncul.

#### Membatalkan Proses

1. **Klik tombol `Cancel`** untuk membatalkan proses penambahan atau pengeditan anggota.

#### Memilih Anggota dari Tabel

1. **Klik pada salah satu baris anggota** di tabel untuk melihat detail anggota tersebut pada form input.

#### Mencari Anggota

1. Gunakan kolom pencarian untuk mencari anggota berdasarkan:
   - **ID Anggota**
   - **Nama**
2. Tabel akan menampilkan hasil pencarian secara dinamis.
   ![image](https://github.com/user-attachments/assets/e0dab13e-c807-4202-8fa1-f430e5fee7bd)


### Tampilan Pustakawan 🧑🏻‍🤝‍👩🏽

#### Menambahkan Pustakawan

1. **Klik tombol `Add`** untuk memulai proses penambahan pustakawan baru.
2. Isi detail pustakawan pada kolom yang tersedia:
   - **ID Pustakawan**
   - **Nama**
   - **Email**
3. **Klik tombol `Update`** untuk menyimpan pustakawan baru.

#### Mengedit Pustakawan

1. Pilih pustakawan yang ingin diedit dari tabel.
2. **Klik tombol `Edit`**.
3. Ubah detail pustakawan yang diinginkan.
4. **Klik tombol `Update`** untuk menyimpan perubahan.

#### Menghapus Pustakawan

1. Pilih pustakawan yang ingin dihapus dari tabel.
2. **Klik tombol `Del`**.
3. Konfirmasi penghapusan pada dialog yang muncul.

#### Membatalkan Proses

1. **Klik tombol `Cancel`** untuk membatalkan proses penambahan atau pengeditan pustakawan.

#### Memilih Pustakawan dari Tabel

1. **Klik pada salah satu baris pustakawan** di tabel untuk melihat detail pustakawan tersebut pada form input.

#### Mencari Pustakawan

1. Gunakan kolom pencarian untuk mencari pustakawan berdasarkan:
   - **ID Pustakawan**
   - **Nama**
   - **Email**
2. Tabel akan menampilkan hasil pencarian secara dinamis.
   ![image](https://github.com/user-attachments/assets/3ce956d8-ed62-472b-80d6-00537bcf7ab3)

   
### Tampilan Transaksi 🎁

#### Menambahkan Transaksi

1. **Klik tombol `Add`** untuk memulai proses penambahan transaksi baru.
2. Isi detail transaksi pada kolom yang tersedia:
   - **ID Transaksi**
   - **ID Buku**
   - **ID Anggota**
   - **Tanggal Pinjam**
   - **Tanggal Jatuh Tempo**
   - **Tanggal Kembali**
3. Pilih **status transaksi** dengan mencentang kotak `Status` jika buku telah dikembalikan.
4. **Klik tombol `Update`** untuk menyimpan transaksi baru.

#### Mengedit Transaksi

1. Pilih transaksi yang ingin diedit dari tabel.
2. **Klik tombol `Edit`**.
3. Ubah detail transaksi yang diinginkan.
4. **Klik tombol `Update`** untuk menyimpan perubahan.

#### Menghapus Transaksi

1. Pilih transaksi yang ingin dihapus dari tabel.
2. **Klik tombol `Del`**.
3. Konfirmasi penghapusan pada dialog yang muncul.

#### Membatalkan Proses

1. **Klik tombol `Cancel`** untuk membatalkan proses penambahan atau pengeditan transaksi.

#### Memilih Transaksi dari Tabel

1. **Klik pada salah satu baris transaksi** di tabel untuk melihat detail transaksi tersebut pada form input.

#### Mencari Transaksi

1. Gunakan kolom pencarian untuk mencari transaksi berdasarkan:
   - **ID Transaksi**
   - **ID Buku**
   - **ID Anggota**
   - **Tanggal Pinjam**
   - **Tanggal Jatuh Tempo**
   - **Tanggal Kembali**
   - **Status**
2. Tabel akan menampilkan hasil pencarian secara dinamis.
   ![image](https://github.com/user-attachments/assets/a6db9501-d255-4a5d-8250-fc85ec5e64ea)



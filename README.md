# Kanka Dili

Evet, kendi programlama dilini yazdım. Hayır, bunun neden yapıldığını sormayın. Java ile yazılmış, Türkçe sözdizimine sahip bir oyuncak programlama dilidir. Rust değil, Python da değil — Java. Devam edin.

## Kurulum

Önce bu repoyu klonlayın, sonra oturup Java yazdığınıza biraz üzünün:

```bash
git clone https://github.com/Mowassir-Noor/KankaLang.git
cd kanka
javac -d build src/com/kankaLang/kanzi/*.java
```

Hata aldıysanız Java kurulu mu diye kontrol edin. Kuruluysa tekrar hata aldıysanız bu sizin probleminiz.

## Kullanım

Yeni bir dosya oluşturun (`test.knk`). Evet, uzantısı `.knk`. Çünkü neden olmasın:

```
degisken isim = "Kanka";
yazdir "Merhaba, " + isim + "!";
```

Çalıştırın:

```bash
java -cp build com.kankaLang.kanzi.Kanka test.knk
```

Çıktı (umarım):

```
Merhaba, Kanka!
```

Bir de REPL var, yani her seferinde dosya açmak zorunda değilsiniz. Hayatınız biraz daha az acı verici olacak:

```bash
java -cp build com.kankaLang.kanzi.Kanka
```

## Dokümantasyon

### Genel

Kanka dosyaları `.knk` uzantısıyla kaydedilir. Her İngilizce anahtar kelimenin Türkçe karşılığı da geçerlidir, yani `var` da çalışır `degisken` de — dil sizi yargılamıyor. Yorumlar `//` ile başlar, tıpkı diğer dillerde olduğu gibi, çünkü bu kadarını icat etmeye üşendik.

```
// Bu bir yorum satırıdır, interpreter görmezden gelir
yazdir "Merhaba!";
```

### Değişkenler

Değişkenler `degisken` ile tanımlanır. Tip belirtmenize gerek yok çünkü dil sizin için tahmin ediyor — bazen doğru bile tahmin ediyor.

```
degisken isim = "Kanka";
degisken sayi = 42;
degisken pi = 3.14;
isim = "Yeni Kanka";
sayi = sayi + 1;
```

### Tipler

Sayılar, stringler, boolean'lar ve `bos` var. Evet bu kadar. Generics yok, interface yok, abstract class yok. Sadelik güzeldir.

```
degisken a = 10;
degisken b = 10 + (15 * 20);
degisken c = "iki";
degisken d = 'ok';
degisken e = bos;       // null'ın Türkçesi
degisken f = dogru;     // true'nun Türkçesi
degisken g = yanlis;    // false'un Türkçesi, hayatınız gibi
```

### Yerleşik Fonksiyonlar

Sadece iki tane yerleşik fonksiyon var. Evet, iki. Çok zengin bir standart kütüphane. `yazdir` ekrana basar, `clock()` zamanı saniye olarak verir — muhtemelen kodunuzun ne kadar yavaş olduğunu ölçmek için kullanacaksınız.

```
yazdir "Merhaba Dunya";
degisken a = 10;
{
    degisken b = 20;
    yazdir a + b;
}
yazdir clock();
```

### Koşullar

`eger` koşul doğruysa çalışır, `degilse` ise tahmin edebileceğiniz üzere diğer durum. Zincir de kurabilirsiniz, hayatınızı karmaşıklaştırmak istiyorsanız.

```
degisken a = 10;
eger (a < 20) {
    yazdir "a yirmiden kucuk";
} degilse eger (a < 25) {
    yazdir "a yirmi bes ten kucuk";
} degilse {
    yazdir "a yirmi bes ten buyuk veya esit";
}
```

### Döngüler

`iken` koşul sağlandığı sürece döner. `icin` ise başlangıç, koşul ve artışı tek satırda alır — aynı C'deki gibi, çünkü tekerleği yeniden icat etmeye gerek yok.

```
// iken (while) — sonsuz döngüye girmeyin, lütfen
degisken i = 0;
iken (i < 5) {
    yazdir i;
    i = i + 1;
}

// icin (for) — daha şık görünmek isteyenler için
icin (degisken j = 1; j <= 10; j = j + 1) {
    yazdir j;
}
```

### Fonksiyonlar

Fonksiyonlar `fonk` ile tanımlanır, `dondur` ile değer döndürülür. Birinci sınıf değerler olduklarından değişkene atanabilirler — bunu anlayamazsanız closure bölümünde kafanız daha çok karışacak.

```
fonk selamla(isim) {
    yazdir "Merhaba, " + isim + "!";
}

fonk topla(a, b) {
    dondur a + b;
}

selamla("Kanka");
yazdir topla(3, 7);  // 10, matematik çalışıyor, şaşırtıcı
```

### Closure (Kapatma)

Fonksiyonlar dış kapsamı yakalar. Bunu anlatmak zor, ama kısaca: fonksiyon, tanımlandığı yerdeki değişkenleri unutmuyor. Hafızası iyi, sizinkinin aksine.

```
fonk sayac_olustur() {
    degisken n = 0;
    fonk artir() {
        n = n + 1;
        dondur n;
    }
    dondur artir;
}

degisken sayac = sayac_olustur();
yazdir sayac();  // 1
yazdir sayac();  // 2
yazdir sayac();  // 3 — evet, sayıyor. Mucize.
```

### Özyineleme

Fonksiyonlar kendi kendini çağırabilir. Stack overflow'a kadar. Eğlenceli.

```
fonk faktoriyel(n) {
    eger (n <= 1) dondur 1;
    dondur n * faktoriyel(n - 1);
}

fonk fibonacci(n) {
    eger (n <= 0) dondur 0;
    eger (n == 1) dondur 1;
    dondur fibonacci(n - 1) + fibonacci(n - 2);
}

yazdir faktoriyel(5);   // 120
yazdir fibonacci(10);   // 55, ve makul sürede biter
```

### Anahtar Kelime Referansı

Ezberleyemezseniz buraya bakın. Utanmayın, hepimiz bakıyoruz.

| Türkçe     | İngilizce | Açıklama              |
|------------|-----------|-----------------------|
| `degisken` | `var`     | Değişken tanımlama    |
| `yazdir`   | `print`   | Ekrana yazdırma       |
| `eger`     | `if`      | Koşul                 |
| `degilse`  | `else`    | Koşulun yanlış dalı   |
| `iken`     | `while`   | Koşullu döngü         |
| `icin`     | `for`     | Sayaçlı döngü         |
| `fonk`     | `fun`     | Fonksiyon tanımlama   |
| `dondur`   | `return`  | Değer döndürme        |
| `dogru`    | `true`    | Boolean doğru         |
| `yanlis`   | `false`   | Boolean yanlış        |
| `bos`      | `nil`     | Boş değer             |
| `ve`       | `and`     | Mantıksal ve          |
| `veya`     | `or`      | Mantıksal veya        |
| `sinif`    | `class`   | Sınıf tanımlama       |
| `ust`      | `super`   | Üst sınıf erişimi     |
| `bu`       | `this`    | Nesne referansı       |

## Testler

`tests/` klasöründe dilin özelliklerini test eden `.knk` dosyaları var. Bunları çalıştırın, her şey doğru çıkıyorsa tebrikler — bir şeyleri bozmadınız. Henüz.

```bash
java -cp build com.kankaLang.kanzi.Kanka tests/01_degiskenler.knk
java -cp build com.kankaLang.kanzi.Kanka tests/05_fonksiyonlar.knk
```

## Geliştirme

Kanka, tree-walk interpreter mimarisini kullanır. Süper optimize değil ama çalışıyor, bu yeterli:

```
Kaynak (.knk) → Scanner → Parser → Resolver → Interpreter
```

| Dosya               | Rol                                                    |
|---------------------|--------------------------------------------------------|
| `Kanka.java`        | Giriş noktası, REPL ve dosya çalıştırıcı               |
| `Scanner.java`      | Kaynak kodu tokenlere ayırır, karakterleri tanır        |
| `Parser.java`       | Tokenlerden AST üretir, özyinelemeli iniş yöntemiyle   |
| `Resolver.java`     | Statik kapsam analizi yapar, değişkenleri çözümler     |
| `Interpreter.java`  | AST'yi doğrudan yürütür, asıl işi yapan kısım bu       |
| `Environment.java`  | Değişken ortam zincirini yönetir                       |


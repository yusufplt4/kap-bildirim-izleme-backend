# KAP Bildirim İzleme ve Filtreleme Modülü

KAP bildirimlerinin izlenmesi, filtrelenmesi ve yönetilmesi amacıyla geliştirilmiş web uygulamasıdır.

Proje, Spring Boot tabanlı backend ve React tabanlı frontend mimarisi kullanılarak geliştirilmiştir.

## Özellikler

- KAP bildirimlerinin görüntülenmesi
- KAP Datafeed entegrasyonu
- Şirket bazlı bildirim filtreleme
- Bildirim konusu bazlı filtreleme
- Tarih aralığına göre bildirim sorgulama
- Kayıtlı filtre oluşturma, yükleme ve silme
- Konsolide bildirim filtreleme
- JWT tabanlı authentication
- USER ve ADMIN rol yönetimi
- Admin kullanıcı oluşturma
- Yeni bildirim kontrolü
- PostgreSQL veritabanı entegrasyonu

## Kullanılan Teknolojiler

### Backend

- Java 17
- Spring Boot
- Spring Security
- Spring Data JPA
- Hibernate
- JWT
- PostgreSQL
- Maven
- RestTemplate

### Frontend

- React
- Vite
- JavaScript
- Ant Design
- Zustand

## Mimari

Backend tarafında katmanlı mimari kullanılmıştır.


Controller
   ↓
Service Interface
   ↓
Service Implementation
   ↓
Repository
   ↓
PostgreSQL

## Güvenlik

- JWT tabanlı authentication kullanılmaktadır.
- USER ve ADMIN rol yetkilendirmesi bulunmaktadır.
- Kullanıcı şifreleri BCrypt ile saklanmaktadır.
- Hassas bilgiler environment variable olarak yönetilmektedir.

## Environment Variables

Uygulamanın çalıştırılması için aşağıdaki değişkenlerin tanımlanması gerekir:

```env
DB_PASSWORD=
JWT_SECRET=
KAP_DATAFEED_BASE_URL=
KAP_HIM_BASE_URL=
```

Gerçek değerler repository içerisinde tutulmamaktadır.

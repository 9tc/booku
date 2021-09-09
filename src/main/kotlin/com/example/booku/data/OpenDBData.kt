package com.example.booku.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable data class OpenDBData(
    val onix: Onix,
    val hanmoto: Hanmoto,
    val summary: Summary
) {
    @Serializable data class Onix (
        @SerialName("RecordReference") val recordReference: String? = null,
        @SerialName("NotificationType") val notificationType: String? = null,
        @SerialName("ProductIdentifier") val productIdentifier: ProductIdentifier? = null,
        @SerialName("DescriptiveDetail") val descriptiveDetail: DescriptiveDetail? = null,
        @SerialName("CollateralDetail") val collateralDetail: CollateralDetail? = null,
        @SerialName("PublishingDetail") val publishingDetail: PublishingDetail? = null,
        @SerialName("ProductSupply") val productSupply: ProductSupply? = null
            ) {
        @Serializable data class ProductIdentifier(
            @SerialName("ProductIDType") val productIDType: String? = null,
            @SerialName("IDValue") val idValue: String? = null
        )

        @Serializable data class DescriptiveDetail (
            @SerialName("ProductComposition") val productComposition: String? = null,
            @SerialName("ProductForm") val productForm: String? = null,
            @SerialName("ProductFormDetail") val productFormDetail: String? = null,
            @SerialName("Measure") val measure: List<Measure>? = null,
            @SerialName("TitleDetail") val titleDetail: TitleDetail? = null,
            @SerialName("Contributor") val contributor: List<Contributor>? = null,
            @SerialName("Language") val language: List<Language>,
            @SerialName("Extent") val extent: List<Extent>? = null,
            @SerialName("Subject") val subject: List<Subject>? = null,
            @SerialName("Audience") val audience: List<Audience>? = null
                ) {
            @Serializable data class TitleDetail (
                @SerialName("TitleType") val titleType: String? = null,
                @SerialName("TitleElement") val titleElement: TitleElement? = null
                    ) {
                @Serializable data class TitleElement (
                    @SerialName("TitleElementLevel") val titleElementLevel: String? = null,
                    @SerialName("TitleText") val titleText: TitleText? = null
                        ) {
                    @Serializable data class TitleText (
                        val collationkey: String? = null,
                        val content: String? = null
                        )
                }
            }

            @Serializable data class Contributor(
                @SerialName("SequenceNumber") val sequenceNumber: String? = null,
                @SerialName("ContributorRole") val ContributorRole: List<String>? = null,
                @SerialName("PersonName") val personName: PersonName? = null,
                @SerialName("BiographicalNote") val biographicalNote: String? = null
            ) {
                @Serializable data class PersonName(
                    val collationkey: String? = null,
                    val content: String? = null
                )
            }

            @Serializable data class Language(
                @SerialName("LanguageRole") val languageRole: String? = null,
                @SerialName("LanguageCode") val languageCode: String? = null
            )

            @Serializable data class Extent(
                @SerialName("ExtentType") val extentType: String? = null,
                @SerialName("ExtentValue") val extentValue: String? = null,
                @SerialName("ExtentUnit") val extentUnit: String? = null
            )

            @Serializable data class Subject(
                @SerialName("MainSubject") val mainSubject: String? = null,
                @SerialName("SubjectSchemeIdentifier") val subjectSchemeIdentifier: String? = null,
                @SerialName("SubjectCode") val subjectCode: String? = null,
                @SerialName("SubjectHeadingText") val subjectHeadingText: String? = null
            )

            @Serializable data class Audience(
                @SerialName("AudienceCodeType") val audienceCodeType: String? = null,
                @SerialName("AudienceCodeValue") val audienceCodeValue: String? = null
            )

            @Serializable data class Measure (
                @SerialName("MeasureType") val measureType: String? = null,
                @SerialName("Measurement") val measurement: String? = null,
                @SerialName("MeasureUnitCode") val measureUnitCode: String? = null
            )
        }

        @Serializable data class CollateralDetail(
            @SerialName("TextContent") val textContent: List<TextContent>? = null,
            @SerialName("SupportingResource") val supportingResource: List<SupportingResource>? = null

        ) {
            @Serializable data class TextContent(
                @SerialName("TextType") val textType: String? = null,
                @SerialName("ContentAudience") val contentAudience: String? = null,
                @SerialName("Text") val text: String? = null
            )

            @Serializable data class SupportingResource(
                @SerialName("ResourceContentType") val resourceContentType: String? = null,
                @SerialName("ContentAudience") val contentAudience: String? = null,
                @SerialName("ResourceMode") val resourceMode: String? = null,
                @SerialName("ResourceVersion") val resourceVersion: List<ResourceVersion>? = null

            ) {
                @Serializable data class ResourceVersion(
                    @SerialName("ResourceForm") val resourceForm: String? = null,
                    @SerialName("ResourceVersionFeature") val resourceVersionFeature: List<ResourceVersionFeature>? = null,
                    @SerialName("ResourceLink") val resourceLink: String? = null,

                ) {
                    @Serializable data class ResourceVersionFeature(
                        @SerialName("ResourceVersionFeatureType") val resourceVersionFeatureType: String? = null,
                        @SerialName("FeatureValue") val featureValue: String? = null
                    )
                }
            }
        }

        @Serializable data class PublishingDetail(
            @SerialName("Imprint") val imprint: Imprint? = null,
            @SerialName("Publisher") val publisher: Publisher? = null,
            @SerialName("PublishingDate") val publishingDate: List<PublishingDate>? = null,


        ) {
            @Serializable data class Imprint(
                @SerialName("ImprintIdentifier") val imprintIdentifier: List<ImprintIdentifier>? = null,
                @SerialName("ImprintName") val ImprintName: String? = null
            ) {
                @Serializable data class ImprintIdentifier(
                    @SerialName("ImprintIDType") val imprintIDType: String? = null,
                    @SerialName("IDValue") val idValue: String? = null
                )
            }

            @Serializable data class Publisher(
                @SerialName("PublishingRole") val publishingRole: String? = null,
                @SerialName("PublisherIdentifier") val publisherIdentifier: List<PublisherIdentifier>? = null,
                @SerialName("PublisherName") val publisherName: String? = null
            ) {
                @Serializable data class PublisherIdentifier(
                    @SerialName("PublisherIDType") val publisherIDType: String? = null,
                    @SerialName("IDValue") val idValue: String? = null
                )
            }

            @Serializable data class PublishingDate(
                @SerialName("PublishingDateRole") val publishingDateRole: String? = null,
                @SerialName("Date") val date: String? = null
            )
        }

        @Serializable data class ProductSupply(
            @SerialName("SupplyDetail") val supplyDetail: SupplyDetail? = null
        ) {
            @Serializable data class SupplyDetail (
                @SerialName("ReturnsConditions") val returnsConditions: ReturnsConditions? = null,
                @SerialName("ProductAvailability") val productAvailability: String? = null,
                @SerialName("Price") val price: List<Price>? = null
            ) {
                @Serializable data class ReturnsConditions(
                    @SerialName("ReturnsCodeType") val returnsCodeType: String? = null,
                    @SerialName("ReturnsCode") val returnsCode: String? = null
                )

                @Serializable data class Price(
                    @SerialName("PriceType") val priceType: String? = null,
                    @SerialName("PriceAmount") val priceAmount: String? = null,
                    @SerialName("CurrencyCode") val currencyCode: String? = null
                )
            }
        }
    }

    @Serializable data class Hanmoto (
        val datemodified: String? = null,
        val datecreated: String? = null
        )

    @Serializable data class Summary (
        val isbn: String? = null,
        val title: String? = null,
        val volume: String? = null,
        val series: String? = null,
        val publisher: String? = null,
        val pubdate: String? = null,
        val cover: String? = null,
        val author: String? = null
        )
}
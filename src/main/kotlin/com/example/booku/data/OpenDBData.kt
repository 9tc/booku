package com.example.booku.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable data class OpenDBData(
    val onix: Onix,
    val hanmoto: Hanmoto,
    val summary: Summary
) {
    @Serializable data class Onix (
        @SerialName("RecordReference") val recordReference: String,
        @SerialName("NotificationType") val notificationType: String,
        @SerialName("ProductIdentifier") val productIdentifier: ProductIdentifier,
        @SerialName("DescriptiveDetail") val descriptiveDetail: DescriptiveDetail,
        @SerialName("CollateralDetail") val collateralDetail: CollateralDetail,
        @SerialName("PublishingDetail") val publishingDetail: PublishingDetail,
        @SerialName("ProductSupply") val productSupply: ProductSupply
            ) {
        @Serializable data class ProductIdentifier(
            @SerialName("ProductIDType") val productIDType: String,
            @SerialName("IDValue") val idValue: String
        )

        @Serializable data class DescriptiveDetail (
            @SerialName("ProductComposition") val productComposition: String,
            @SerialName("ProductForm") val productForm: String,
            @SerialName("ProductFormDetail") val productFormDetail: String,
            @SerialName("TitleDetail") val titleDetail: TitleDetail,
            @SerialName("Contributor") val contributor: List<Contributor>,
            @SerialName("Language") val language: List<Language>,
            @SerialName("Extent") val extent: List<Extent>? = null,
            @SerialName("Subject") val subject: List<Subject>,
            @SerialName("Audience") val audience: List<Audience>
                ) {
            @Serializable data class TitleDetail (
                @SerialName("TitleType") val titleType: String,
                @SerialName("TitleElement") val titleElement: TitleElement
                    ) {
                @Serializable data class TitleElement (
                    @SerialName("TitleElementLevel") val titleElementLevel: String,
                    @SerialName("TitleText") val titleText: TitleText
                        ) {
                    @Serializable data class TitleText (
                        val collationkey: String,
                        val content: String
                        )
                }
            }

            @Serializable data class Contributor(
                @SerialName("SequenceNumber") val sequenceNumber: String,
                @SerialName("ContributorRole") val ContributorRole: List<String>,
                @SerialName("PersonName") val personName: PersonName,
                @SerialName("BiographicalNote") val biographicalNote: String? = ""
            ) {
                @Serializable data class PersonName(
                    val collationkey: String,
                    val content: String
                )
            }

            @Serializable data class Language(
                @SerialName("LanguageRole") val languageRole: String,
                @SerialName("LanguageCode") val languageCode: String
            )

            @Serializable data class Extent(
                @SerialName("ExtentType") val extentType: String,
                @SerialName("ExtentValue") val extentValue: String,
                @SerialName("ExtentUnit") val extentUnit: String
            )

            @Serializable data class Subject(
                @SerialName("MainSubject") val mainSubject: String? = "",
                @SerialName("SubjectSchemeIdentifier") val subjectSchemeIdentifier: String,
                @SerialName("SubjectCode") val subjectCode: String? = "",
                @SerialName("SubjectHeadingText") val subjectHeadingText: String? = ""
            )

            @Serializable data class Audience(
                @SerialName("AudienceCodeType") val audienceCodeType: String,
                @SerialName("AudienceCodeValue") val audienceCodeValue: String
            )
        }

        @Serializable data class CollateralDetail(
            @SerialName("TextContent") val textContent: List<TextContent>? = null,
            @SerialName("SupportingResource") val supportingResource: List<SupportingResource>? = null

        ) {
            @Serializable data class TextContent(
                @SerialName("TextType") val textType: String,
                @SerialName("ContentAudience") val contentAudience: String,
                @SerialName("Text") val text: String
            )

            @Serializable data class SupportingResource(
                @SerialName("ResourceContentType") val resourceContentType: String,
                @SerialName("ContentAudience") val contentAudience: String,
                @SerialName("ResourceMode") val resourceMode: String,
                @SerialName("ResourceVersion") val resourceVersion: List<ResourceVersion>

            ) {
                @Serializable data class ResourceVersion(
                    @SerialName("ResourceForm") val resourceForm: String,
                    @SerialName("ResourceVersionFeature") val resourceVersionFeature: List<ResourceVersionFeature>,
                    @SerialName("ResourceLink") val resourceLink: String,

                ) {
                    @Serializable data class ResourceVersionFeature(
                        @SerialName("ResourceVersionFeatureType") val resourceVersionFeatureType: String,
                        @SerialName("FeatureValue") val featureValue: String
                    )
                }
            }
        }

        @Serializable data class PublishingDetail(
            @SerialName("Imprint") val imprint: Imprint,
            @SerialName("Publisher") val publisher: Publisher? = null,
            @SerialName("PublishingDate") val publishingDate: List<PublishingDate>,


        ) {
            @Serializable data class Imprint(
                @SerialName("ImprintIdentifier") val imprintIdentifier: List<ImprintIdentifier>,
                @SerialName("ImprintName") val ImprintName: String
            ) {
                @Serializable data class ImprintIdentifier(
                    @SerialName("ImprintIDType") val imprintIDType: String,
                    @SerialName("IDValue") val idValue: String
                )
            }

            @Serializable data class Publisher(
                @SerialName("PublishingRole") val publishingRole: String? = "",
                @SerialName("PublisherIdentifier") val publisherIdentifier: List<PublisherIdentifier>?,
                @SerialName("PublisherName") val publisherName: String? = ""
            ) {
                @Serializable data class PublisherIdentifier(
                    @SerialName("PublisherIDType") val publisherIDType: String? = "",
                    @SerialName("IDValue") val idValue: String? = ""
                )
            }

            @Serializable data class PublishingDate(
                @SerialName("PublishingDateRole") val publishingDateRole: String,
                @SerialName("Date") val date: String
            )
        }

        @Serializable data class ProductSupply(
            @SerialName("SupplyDetail") val supplyDetail: SupplyDetail
        ) {
            @Serializable data class SupplyDetail (
                @SerialName("ReturnsConditions") val returnsConditions: ReturnsConditions? = null,
                @SerialName("ProductAvailability") val productAvailability: String,
                @SerialName("Price") val price: List<Price>
            ) {
                @Serializable data class ReturnsConditions(
                    @SerialName("ReturnsCodeType") val returnsCodeType: String,
                    @SerialName("ReturnsCode") val returnsCode: String
                )

                @Serializable data class Price(
                    @SerialName("PriceType") val priceType: String,
                    @SerialName("PriceAmount") val priceAmount: String,
                    @SerialName("CurrencyCode") val currencyCode: String
                )
            }
        }
    }

    @Serializable data class Hanmoto (
        val datemodified: String,
        val datecreated: String
        )

    @Serializable data class Summary (
        val isbn: String,
        val title: String,
        val volume: String,
        val series: String,
        val publisher: String,
        val pubdate: String,
        val cover: String,
        val author: String
        )
}
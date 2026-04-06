import "./globals.css";
import { Metadata } from "next";
import { getProfileSection } from "@/utils/profileData";

// Get basic info
const basics = getProfileSection("basics");
const siteUrl = "https://agaresd47.github.io/HW_Project";

export const metadata: Metadata = {
  title: {
    template: `%s | ${basics.name}`,
    default: `${basics.name} - ${basics.title}`,
  },
  description: basics.metaDescription,
  keywords: [
    "conversational ai",
    "ai product",
    "dialogue evaluation",
    "ai companion",
    "chinese llm",
  ],
  authors: [{ name: basics.name }],
  creator: basics.name,
  metadataBase: new URL(siteUrl),
  openGraph: {
    type: "website",
    locale: "en_US",
    url: siteUrl,
    siteName: `${basics.name} - AI Product Portfolio`,
    title: `${basics.name} - ${basics.title}`,
    description: basics.metaDescription,
    images: [
      {
        url: `${siteUrl}/profile.jpg`,
        width: 1200,
        height: 630,
        alt: `${basics.name} - AI Product Portfolio`,
      },
    ],
  },
  twitter: {
    card: "summary_large_image",
    title: `${basics.name} - ${basics.title}`,
    description: basics.metaDescription,
    images: [`${siteUrl}/profile.jpg`],
    creator: "@Agaresd47",
  },
  robots: {
    index: true,
    follow: true,
    googleBot: {
      index: true,
      follow: true,
      "max-video-preview": -1,
      "max-image-preview": "large",
      "max-snippet": -1,
    },
  },
  alternates: {
    canonical: siteUrl,
  },
};

export default function RootLayout({
  children,
}: {
  children: React.ReactNode;
}) {
  return (
    <html lang="en">
      <body>{children}</body>
    </html>
  );
}

import { Metadata } from "next";
import { PortfolioPage } from "@/app/page";
import { getProfileSection } from "@/utils/profileData";

export const metadata: Metadata = {
  title: getProfileSection("basics", "zh").name,
  description: getProfileSection("basics", "zh").metaDescription,
};

export default function ChineseHome() {
  return <PortfolioPage locale="zh" />;
}

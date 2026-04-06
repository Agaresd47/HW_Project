import { Metadata } from "next";
import Header from "@/components/Header";
import HeroSection from "@/components/HeroSection";
import AboutSection from "@/components/AboutSection";
import ExperienceSection from "@/components/ExperienceSection";
import ProjectsSection from "@/components/ProjectsSection";
import SkillsSection from "@/components/SkillsSection";
import EducationSection from "@/components/EducationSection";
import ContactSection from "@/components/ContactSection";
import Footer from "@/components/Footer";
import ScrollToTop from "@/components/ScrollToTop";
import AnimateSection from "@/components/AnimateSection";
import HashLinkHandler from "@/components/HashLinkHandler";
import { getProfileSection, type Locale } from "@/utils/profileData";

// Define metadata for SEO
export const metadata: Metadata = {
  title: getProfileSection("basics", "en").name,
  description: getProfileSection("basics", "en").metaDescription,
};

// Component map for dynamic section rendering
const sectionComponents = {
  about: AboutSection,
  experience: ExperienceSection,
  projects: ProjectsSection,
  skills: SkillsSection,
  education: EducationSection,
  contact: ContactSection,
};

function PortfolioPage({ locale = "en" }: { locale?: Locale }) {
  const navigationItems = getProfileSection("navigation", locale);

  return (
    <div className="min-h-screen bg-gradient-to-b from-gray-50 to-gray-100 dark:from-neutral-900 dark:to-neutral-800 font-[family-name:var(--font-geist-sans)]">
      <HashLinkHandler />
      <Header locale={locale} />
      <main>
        <HeroSection locale={locale} />

        {navigationItems.map((item) => {
          const sectionId = item.href.replace("#", "");
          const SectionComponent =
            sectionComponents[sectionId as keyof typeof sectionComponents];

          if (!SectionComponent) return null;

          return (
            <AnimateSection key={sectionId} id={sectionId}>
              <SectionComponent locale={locale} />
            </AnimateSection>
          );
        })}
      </main>
      <Footer locale={locale} />
      <ScrollToTop />
    </div>
  );
}

export default function Home() {
  return <PortfolioPage locale="en" />;
}

export { PortfolioPage };

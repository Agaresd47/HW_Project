import Image from "next/image";
import { getProfileSection, type Locale } from "@/utils/profileData";

export default function HeroSection({ locale = "en" }: { locale?: Locale }) {
  const basics = getProfileSection("basics", locale);
  const isZh = locale === "zh";

  return (
    <section className="container flex flex-col items-center px-6 py-20 mx-auto">
      {/* Content section - text and photo */}
      <div className="flex flex-col w-full mb-12 max-w-7xl lg:flex-row lg:justify-between lg:items-center">
        {/* Text content */}
        <div className="mb-10 lg:w-1/2 lg:mb-0">
          <h1 className="mb-4 text-4xl font-bold md:text-5xl">
            <span className="block">{basics.name}</span>
          </h1>
          <h2 className="mb-6 text-xl text-gray-600 md:text-2xl dark:text-gray-300">
            {basics.title}
          </h2>

          {/* Render summary paragraphs from array */}
          {basics.summaries &&
            Array.isArray(basics.summaries) &&
            basics.summaries.map((paragraph, index) => (
              <p
                key={index}
                className={`text-lg max-w-lg ${index > 0 ? "mt-4" : ""}`}
              >
                {paragraph}
              </p>
            ))}
        </div>

        {/* Photo */}
        <div className="flex justify-center lg:w-2/5">
          <div className="relative overflow-hidden border border-white shadow-lg rounded-3xl w-72 h-[24rem] md:w-96 md:h-[30rem] dark:border-gray-800">
            <Image
              src={basics.profileImage}
              alt={basics.name}
              fill
              sizes="(max-width: 768px) 288px, (max-width: 1024px) 384px, 384px"
              className="object-cover"
              priority
            />
          </div>
        </div>
      </div>

      {/* Action buttons - now below both text and photo */}
      <div className="flex flex-col justify-center gap-4 sm:flex-row">
        <a
          href={basics.resumeUrl}
          target="_blank"
          rel="noopener noreferrer"
          className="px-6 py-3 text-center rounded-full btn-primary"
        >
          {isZh ? "打开简历" : "Open Resume"}
        </a>
        <a
          href="#projects"
          className="px-6 py-3 text-center rounded-full btn-secondary"
        >
          {isZh ? "查看项目" : "View Projects"}
        </a>
      </div>
    </section>
  );
}

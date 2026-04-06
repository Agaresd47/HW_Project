import Link from "next/link";
import MobileMenuToggle from "./MobileMenuToggle";
import { getProfileSection, type Locale } from "@/utils/profileData";

export default function Header({ locale = "en" }: { locale?: Locale }) {
  const basics = getProfileSection("basics", locale);
  const navigation = getProfileSection("navigation", locale);
  const contact = getProfileSection("contact", locale);
  const isZh = locale === "zh";

  return (
    <header
      className={`sticky top-0 z-20 backdrop-blur-sm bg-white/70 dark:bg-black/70 border-b border-gray-200 dark:border-gray-800`}
    >
      <div className="container flex items-center justify-between px-6 py-4 mx-auto">
        <div className="flex items-center gap-4">
          <Link
            href={isZh ? "/zh" : "/"}
            className="transition-colors hover:text-blue-600 dark:hover:text-blue-400"
          >
            <h1 className="text-xl font-bold tracking-tight">{basics.name}</h1>
          </Link>
          <div className="items-center hidden gap-4 text-sm md:flex">
            {contact.socialLinks.github && (
              <a
                href={contact.socialLinks.github}
                target="_blank"
                rel="noopener noreferrer"
                className="text-gray-600 dark:text-gray-300 hover:text-blue-600 dark:hover:text-blue-400"
              >
                GitHub
              </a>
            )}
            {contact.socialLinks.linkedin && (
              <a
                href={contact.socialLinks.linkedin}
                target="_blank"
                rel="noopener noreferrer"
                className="text-gray-600 dark:text-gray-300 hover:text-blue-600 dark:hover:text-blue-400"
              >
                LinkedIn
              </a>
            )}
          </div>
        </div>

        {/* Desktop navigation with CV button */}
        <nav className="items-center hidden md:flex">
          <ul className="flex mr-6 space-x-6">
            {navigation.map((item) => (
              <li key={item.href}>
                <a
                  href={item.href}
                  className={`text-gray-600 dark:text-gray-300 hover:text-blue-600 dark:hover:text-blue-400`}
                >
                  {item.name}
                </a>
              </li>
            ))}
          </ul>

          <div className="flex items-center mr-4 text-sm border rounded-full border-gray-300 dark:border-gray-700 overflow-hidden">
            <Link
              href="/"
              className={`px-3 py-2 ${!isZh ? "bg-gray-900 text-white dark:bg-white dark:text-black" : "text-gray-600 dark:text-gray-300"}`}
            >
              EN
            </Link>
            <Link
              href="/zh"
              className={`px-3 py-2 ${isZh ? "bg-gray-900 text-white dark:bg-white dark:text-black" : "text-gray-600 dark:text-gray-300"}`}
            >
              中文
            </Link>
          </div>

          <a
            href={basics.resumeUrl}
            target="_blank"
            rel="noopener noreferrer"
            className="flex items-center gap-2 px-5 py-2 text-sm font-medium rounded-full shadow-sm btn-primary"
            aria-label={isZh ? "打开简历" : "Open resume"}
          >
            <span>{isZh ? "打开简历" : "Open Resume"}</span>
            <svg
              xmlns="http://www.w3.org/2000/svg"
              width="16"
              height="16"
              viewBox="0 0 24 24"
              fill="none"
              stroke="currentColor"
              strokeWidth="2"
              strokeLinecap="round"
              strokeLinejoin="round"
              className="transition-transform group-hover:translate-y-0.5"
            >
              <path d="M21 15v4a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2v-4"></path>
              <polyline points="7 10 12 15 17 10"></polyline>
              <line x1="12" y1="15" x2="12" y2="3"></line>
            </svg>
          </a>
        </nav>

        <MobileMenuToggle />
      </div>
    </header>
  );
}

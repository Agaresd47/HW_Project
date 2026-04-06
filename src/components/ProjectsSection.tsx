import { getProfileSection, type Locale } from "@/utils/profileData";
import Image from "next/image";

export default function ProjectsSection({ locale = "en" }: { locale?: Locale }) {
  const projects = getProfileSection("projects", locale);
  const isZh = locale === "zh";

  if (!projects || projects.length === 0) {
    return null; // Return null if no projects are found
  }

  return (
    <section id="projects" className="py-20 bg-white dark:bg-neutral-900">
      <div className="container px-6 mx-auto">
        <h2 className="mb-12 text-3xl font-bold text-center">
          {locale === "zh" ? "核心项目" : "Core Projects"}
        </h2>
        <div className="grid grid-cols-1 gap-8 md:grid-cols-2">
          {projects.map((project) => {
            // Check for image property
            const hasImage = !!project.image;

            return (
              <div
                key={project.name}
                className="rounded-lg overflow-hidden shadow-md bg-white dark:bg-neutral-800 transition-transform hover:scale-[1.02]"
              >
                {/* Project header with image if available, otherwise gradient with name */}
                <div
                  className={`h-48 ${
                    !hasImage ? `bg-gradient-to-r ${project.color}` : ""
                  } flex items-center justify-center text-white text-xl font-bold`}
                >
                  {hasImage ? (
                    <Image
                      src={project.image!}
                      alt={project.name}
                      width={400}
                      height={200}
                      className="object-cover w-full h-full"
                    />
                  ) : (
                    project.name
                  )}
                </div>

                <div className="p-6">
                  {project.duration && (
                    <p className="mb-2 text-sm font-medium text-blue-600 dark:text-blue-400">
                      {project.duration}
                    </p>
                  )}

                  {/* Project title */}
                  <h3 className="mb-2 text-xl font-semibold">
                    {project.title}
                  </h3>

                  {/* Project description */}
                  <p className="mb-4 text-gray-600 dark:text-gray-300">
                    {project.description}
                  </p>

                  {/* Feature list */}
                  <ul className="mb-4 space-y-1 text-gray-600 list-disc list-inside dark:text-gray-300">
                    {project.features.map((feature, index) => (
                      <li key={index}>{feature}</li>
                    ))}
                  </ul>

                  {/* Technology tags */}
                  <div className="flex flex-wrap gap-2 mb-4">
                    {project.technologies.slice(0, 5).map((tech, index) => (
                      <span
                        key={index}
                        className="px-2 py-1 text-xs text-blue-800 bg-blue-100 rounded dark:bg-blue-900 dark:text-blue-200"
                      >
                        {tech}
                      </span>
                    ))}
                    {project.technologies.length > 5 && (
                      <span className="px-2 py-1 text-xs text-gray-800 bg-gray-100 rounded dark:bg-gray-800 dark:text-gray-200">
                        +{project.technologies.length - 5} more
                      </span>
                    )}
                  </div>

                  <div className="flex flex-wrap gap-4 text-sm">
                    {project.link && (
                      <a
                        href={project.link}
                        target="_blank"
                        rel="noopener noreferrer"
                        className="text-blue-600 dark:text-blue-400 hover:underline"
                      >
                        {project.linkLabel ||
                          (isZh ? "外部链接" : "External Link")}
                      </a>
                    )}
                    {project.github && (
                      <a
                        href={project.github}
                        target="_blank"
                        rel="noopener noreferrer"
                        className="text-blue-600 dark:text-blue-400 hover:underline"
                      >
                        {project.githubLabel || "GitHub"}
                      </a>
                    )}
                    {project.documentation && (
                      <a
                        href={project.documentation}
                        target="_blank"
                        rel="noopener noreferrer"
                        className="text-blue-600 dark:text-blue-400 hover:underline"
                      >
                        {project.documentationLabel ||
                          (isZh ? "项目文档" : "Documentation")}
                      </a>
                    )}
                  </div>
                </div>
              </div>
            );
          })}
        </div>
      </div>
    </section>
  );
}

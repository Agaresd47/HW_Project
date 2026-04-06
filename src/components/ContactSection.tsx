import { getProfileSection, type Locale } from "@/utils/profileData";

export default function ContactSection({ locale = "en" }: { locale?: Locale }) {
  const contact = getProfileSection("contact", locale);
  const isZh = locale === "zh";

  return (
    <section id="contact" className="py-20">
      <div className="container px-6 mx-auto">
        <h2 className="mb-12 text-3xl font-bold text-center">
          {isZh
            ? "目前正在寻找 2026 年 AI 产品与对话式 AI 全职机会"
            : "Open to 2026 Conversational AI and AI Product Roles"}
        </h2>
        <div className="max-w-md mx-auto">
          <p className="mb-8 text-center">
            {isZh
              ? "我重点关注 AI 产品、对话式 AI、AI 陪伴、模型评测与面向用户的大模型应用研究。如果你的团队在做长期交互、中文对话体验、助手策略或评测体系，欢迎联系我。"
              : `I am actively looking for full-time roles in conversational AI, AI product,
            companion experiences, model evaluation, and user-facing LLM research. If your
            team works on long-horizon interaction quality, assistant strategy, or Chinese
            dialogue systems, I would be glad to connect.`}
          </p>
          <div className="flex flex-col justify-center gap-4 mb-8 sm:flex-row">
            <a
              href={contact.resume}
              target="_blank"
              rel="noopener noreferrer"
              className="px-6 py-3 text-center rounded-full btn-primary"
            >
              {isZh ? "打开简历" : "Open Resume"}
            </a>
            <a
              href={`mailto:${contact.email}`}
              className="px-6 py-3 text-center rounded-full btn-secondary"
            >
              {isZh ? "邮件联系" : "Email Me"}
            </a>
          </div>
        </div>
      </div>
    </section>
  );
}

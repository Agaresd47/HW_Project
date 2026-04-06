# CODEX Notes

## Current Project Location

`C:\Users\agares\OneDrive\0 求职\personal-site`

This repo is the active personal/job-search website project.

## Current State

The site was originally a hand-written static HTML portfolio.
It has now been fully migrated to a Next.js portfolio template based on:

- `https://github.com/kimsengduong/nextjs-portfolio-template`

The current version is much simpler, easier to read, and easier to maintain than the earlier custom static version.

## Primary Goal

This website is now positioned as a **job-search portfolio site**, not an academic-homepage-first site.

Main target directions:

- Machine Learning Engineer
- Applied Scientist
- Quantitative Research
- Medical AI
- LLM systems / applied AI / data-heavy engineering roles

## Repo and Deployment

Repo:

- `Agaresd47/agaresd47.github.io`

Live URLs:

- English: `https://agaresd47.github.io/`
- Chinese: `https://agaresd47.github.io/zh`

Deployment setup:

- GitHub Pages is enabled
- Pages build type is now `workflow`
- Deployment is handled by GitHub Actions, not branch-based legacy Pages
- Static export is enabled in Next.js

Key deployment files:

- [next.config.ts](C:/Users/agares/OneDrive/0%20求职/personal-site/next.config.ts)
- [.github/workflows/deploy-pages.yml](C:/Users/agares/OneDrive/0%20求职/personal-site/.github/workflows/deploy-pages.yml)

## Local Development

Node LTS was installed on this machine during setup.

Use these commands from the repo root:

```powershell
npm install
npm run dev
```

Build check:

```powershell
npm run build
```

This project has already successfully built with:

- static export
- English route `/`
- Chinese route `/zh`

## Content Model

This project is data-driven.

Main content files:

- English content: [src/data/profile.json](C:/Users/agares/OneDrive/0%20求职/personal-site/src/data/profile.json)
- Chinese content: [src/data/profile.zh.json](C:/Users/agares/OneDrive/0%20求职/personal-site/src/data/profile.zh.json)

These files control:

- name
- title
- hero summary
- about text
- experience
- selected work / projects
- skills
- education
- contact links
- navigation labels

## Routing

Current route structure:

- English home: [src/app/page.tsx](C:/Users/agares/OneDrive/0%20求职/personal-site/src/app/page.tsx)
- Chinese home: [src/app/zh/page.tsx](C:/Users/agares/OneDrive/0%20求职/personal-site/src/app/zh/page.tsx)

The bilingual support is implemented through:

- `profile.json` and `profile.zh.json`
- locale-aware component props
- shared page renderer in `src/app/page.tsx`

Locale utility:

- [src/utils/profileData.ts](C:/Users/agares/OneDrive/0%20求职/personal-site/src/utils/profileData.ts)

## Key Components

Main layout and section files:

- [src/components/Header.tsx](C:/Users/agares/OneDrive/0%20求职/personal-site/src/components/Header.tsx)
- [src/components/HeroSection.tsx](C:/Users/agares/OneDrive/0%20求职/personal-site/src/components/HeroSection.tsx)
- [src/components/AboutSection.tsx](C:/Users/agares/OneDrive/0%20求职/personal-site/src/components/AboutSection.tsx)
- [src/components/ExperienceSection.tsx](C:/Users/agares/OneDrive/0%20求职/personal-site/src/components/ExperienceSection.tsx)
- [src/components/ProjectsSection.tsx](C:/Users/agares/OneDrive/0%20求职/personal-site/src/components/ProjectsSection.tsx)
- [src/components/SkillsSection.tsx](C:/Users/agares/OneDrive/0%20求职/personal-site/src/components/SkillsSection.tsx)
- [src/components/EducationSection.tsx](C:/Users/agares/OneDrive/0%20求职/personal-site/src/components/EducationSection.tsx)
- [src/components/ContactSection.tsx](C:/Users/agares/OneDrive/0%20求职/personal-site/src/components/ContactSection.tsx)
- [src/components/Footer.tsx](C:/Users/agares/OneDrive/0%20求职/personal-site/src/components/Footer.tsx)

## Assets

Current production assets:

- photo: [public/profile.jpg](C:/Users/agares/OneDrive/0%20求职/personal-site/public/profile.jpg)
- English resume: [public/Zifei_Dong_Resume.pdf](C:/Users/agares/OneDrive/0%20求职/personal-site/public/Zifei_Dong_Resume.pdf)
- Chinese resume: [public/董自飞简历.pdf](C:/Users/agares/OneDrive/0%20求职/personal-site/public/%E8%91%A3%E8%87%AA%E9%A3%9E%E7%AE%80%E5%8E%86.pdf)

Resume links are intentionally configured to **open in a new tab**, not force download.

## Important UX Decisions Already Made

### 1. Switched away from decorative static design

The earlier custom HTML/CSS version felt too decorative and harder to iterate on.
The current template is intentionally cleaner and more recruiter-readable.

### 2. Reframed for job search

The site is no longer written like a generic academic homepage.
It is framed around:

- full-time opportunities in 2026
- applied ML / AI / quant roles
- practical project execution

### 3. Bilingual support restored

Chinese support was added back after the first Next.js migration.

Current bilingual behavior:

- `/` = English
- `/zh` = Chinese
- language toggle is in the header

### 4. GitHub and LinkedIn moved into the top header

The social links were moved out of the bottom contact section.

Current behavior:

- `GitHub` and `LinkedIn` appear in the top horizontal bar
- they sit to the right of the site name
- mobile menu also includes them
- bottom contact section no longer duplicates them

This change was specifically requested because the footer/contact placement felt less useful.

## Header Structure

Current header contains:

- site name (`Zifei Dong` / `董自飞`)
- GitHub link
- LinkedIn link
- navigation links
- language switch
- resume button

Main file:

- [src/components/Header.tsx](C:/Users/agares/OneDrive/0%20求职/personal-site/src/components/Header.tsx)

Mobile menu file:

- [src/components/MobileMenuToggle.tsx](C:/Users/agares/OneDrive/0%20求职/personal-site/src/components/MobileMenuToggle.tsx)

## Contact Section

The contact section was simplified and now focuses on:

- open to 2026 opportunities
- resume button
- email button

GitHub and LinkedIn were intentionally removed from the bottom contact block after being moved to the header.

## Metadata / SEO

Metadata was updated away from the template defaults and now points to:

- `https://agaresd47.github.io`
- profile image as OG/Twitter image

Main metadata file:

- [src/app/layout.tsx](C:/Users/agares/OneDrive/0%20求职/personal-site/src/app/layout.tsx)

## What Was Already Verified

The following are known-good:

- `npm run build` succeeds locally
- `/` exports statically
- `/zh` exports statically
- GitHub Actions Pages workflow runs successfully
- site is live on GitHub Pages

## Current Git Status

At the time this note was updated, the repo had already gone through:

- migration to Next.js template
- GitHub Pages workflow deployment setup
- bilingual route addition
- header social-link relocation

If a future session starts, first inspect current git status in case the user has made local changes after this note was written.

## Recommended Next Improvements

The most useful next steps are:

1. Tighten hero copy further.
   Make the first screen even more concise and recruiter-facing.

2. Improve `Selected Work`.
   The cards are functional, but they can be rewritten to emphasize outcomes, scale, and execution more directly.

3. Improve Chinese copy quality.
   Some Chinese strings should be reviewed in a UTF-8-safe editor and refined for more natural job-search language.

4. Potentially add one section:
   - `What I’m Looking For`
   or
   - `Featured Projects`

5. Decide whether publications should remain embedded inside `Selected Work` or be split later.

## Important Note About Chinese Text

PowerShell output in this environment sometimes displays Chinese text as mojibake even when the underlying file is correct UTF-8.

Do not assume the file is corrupted just because terminal output looks garbled.
When verifying Chinese files, prefer:

- `Get-Content -Encoding utf8`
- or a UTF-8 aware editor

## Suggested Prompt For Future Codex Sessions

Use something like:

`Open C:\Users\agares\OneDrive\0 求职\personal-site, read CODEX.md first, then continue refining the bilingual job-search portfolio. Keep the design clean and recruiter-readable. Prioritize hero copy, selected work, and Chinese text quality.`
